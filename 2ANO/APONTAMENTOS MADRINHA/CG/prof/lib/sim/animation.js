//
// Classe KeyFrameAnimator descende de Sim.Object
//
Sim.KeyFrameAnimator = function() {
	//
    Sim.Object.call();
	//	    		
	//	interpolações
	//
	this.interps = [];
	this.running = false;
}
//
Sim.KeyFrameAnimator.prototype = new Sim.Object;
//
//	Inicialização com parâmetros
//	
Sim.KeyFrameAnimator.prototype.init = function(param) {
	//
	param = param || {}; // normalizar "param"
	//
	if (param.interps) {
		this.createInterpolators(param.interps);
	}	    		
	//
	//	Configurar atributos locais a partir de "param"
	//
	this.duration = param.duration ? param.duration : Sim.KeyFrameAnimator.default_duration;
	this.loop = param.loop ? param.loop : false;
}
//
//	Fazer interpoladores
//
Sim.KeyFrameAnimator.prototype.createInterpolators = function(interps) {
	var i, len = interps.length;
	for (i = 0; i < len; i++) {
		var param = interps[i];
		var interp = new Sim.Interpolator();
		interp.init({ keys: param.keys, values: param.values, target: param.target });
		this.interps.push(interp);
	}
}
//
// Começar animação
//
Sim.KeyFrameAnimator.prototype.start = function() {
	if (this.running)
		return;	
	this.startTime = Date.now();
	this.running = true;
}
//
//	Parar animação
//
Sim.KeyFrameAnimator.prototype.stop = function() {
	this.running = false;
	this.publish("complete");
}
//
//	Actualizar animação: calcular a frame chave
//
Sim.KeyFrameAnimator.prototype.update = function() {
	if (!this.running)
		return;
	
	var now = Date.now();
	//
	//	Tempo (cíclico) decorrido (desde o início)
	//
	var deltat = (now - this.startTime) % this.duration;
	//
	//	Número de ciclos decorridos
	//
	var nCycles = Math.floor((now - this.startTime) / this.duration);
	//
	//	Fracção correspondente ao tempo decorrido
	//
	var fract = deltat / this.duration;
	//
	//	Verificar se a animação (não) é cíclica: this.loop == true
	//
	if (nCycles >= 1 && !this.loop) {
		this.running = false;
		this.publish("complete");
		//
		//	Passar para o fim da animação
		//
		frac = 1.0;
	}
	var i, len = this.interps.length;
	for (i = 0; i < len; i++) {
		this.interps[i].interp(fract);
	}
}
//
//	Parâmetros estáticos
//
Sim.KeyFrameAnimator.default_duration = 1000;
//
//	Classe Interpolator descende de Sim.Object
//
Sim.Interpolator = function() {
	//
	Sim.Object.call();
	//
	//	chaves, valores e alvo
	//	
	this.keys = [];
	this.values = [];
	this.target = null;
	//
	this.running = false;
}
//
Sim.Interpolator.prototype = new Sim.Object;
//
//	Inicialização com parâmetros
//	
Sim.Interpolator.prototype.init = function(param) {
	param = param || {};
	//
	if (param.keys && param.values) {
		this.setValue(param.keys, param.values);
	}	    		
	//
	this.target = param.target ? param.target : null;
}
//
//	Definir chaves : valores
//
Sim.Interpolator.prototype.setValue = function(keys, values) {
	this.keys = [];
	this.values = [];
	if (keys && keys.length && values && values.length) {
		this.copyKeys(keys, this.keys);
		this.copyValues(values, this.values);
	}
}
//
//	Funções auxiliares para garantir cópia correcta de chaves e valores	
//
Sim.Interpolator.prototype.copyKeys = function(from, to) {
	var i = 0, len = from.length;
	for (i = 0; i < len; i++) {
		to[i] = from[i];
	}
}
//
Sim.Interpolator.prototype.copyValues = function(from, to) {
	var i = 0, len = from.length;
	for (i = 0; i < len; i++) {
		var val = {};
		this.copyValue(from[i], val);
		to[i] = val;
	}
}
//
Sim.Interpolator.prototype.copyValue = function(from, to) {
	for ( var property in from ) {		
		if ( from[ property ] === null ) {		
			continue;		
		}
		to[ property ] = from[ property ];
	}
}
//
//	Métodos para interpolar e "tween"
//
Sim.Interpolator.prototype.interp = function(fract) {
	//
	var value;
	var i, len = this.keys.length;
	//
	if (fract == this.keys[0]) {
		value = this.values[0];
	} else if (fract >= this.keys[len - 1]) {
		value = this.values[len - 1];
	}
	//
	for (i = 0; i < len - 1; i++) {
		var key1 = this.keys[i];
		var key2 = this.keys[i + 1];

		if (fract >= key1 && fract <= key2) {
			var val1 = this.values[i];
			var val2 = this.values[i + 1];
			value = this.tween(val1, val2, (fract - key1) / (key2 - key1));
		}
	}
	//
	if (this.target) {
		this.copyValue(value, this.target);
	} else {
		this.publish("value", value);
	}
}
//
Sim.Interpolator.prototype.tween = function(from, to, fract) {
	var value = {};
	for ( var property in from ) {
		//
		if ( from[ property ] === null ) {		
			continue;		
		}
		//
		var range = to[property] - from[property];
		var delta = range * fract;
		value[ property ] = from[ property ] + delta;
	}
	
	return value;
}
