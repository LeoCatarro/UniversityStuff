//
// Construtor RobotApp específico
//
RobotApp = function() {
	Sim.App.call(this);
}
//
// Subclasse de Sim.App
//
RobotApp.prototype = new Sim.App();
//
// Inicialização específica
//
RobotApp.prototype.init = function(param) {
	//
	Sim.App.prototype.init.call(this, param);
	//
	var light = new THREE.DirectionalLight( 0xeeeeff, 1);
	light.position.set(0, 0, 1);
	this.scene.add(light);
	//
	this.camera.position.set(0, 2.333, 8);
	//
    // Fazer um Robot e acrescentá-lo à simulação
    var robot = new Robot();
    robot.init();
    this.addObject(robot);    
    //
    // É necessário ajustar a pose do robot
    //
    this.root.rotation.y = Math.PI / 4;
    this.robot = robot;
    this.animating = false;
    //
    this.robot.subscribe("complete", this, this.onAnimationComplete)
}
//
RobotApp.prototype.update = function() {
	this.root.rotation.y += 0.005;
	Sim.App.prototype.update.call(this);
}
//
RobotApp.prototype.handleMouseUp = function(x, y) {
	this.animating = !this.animating;
	this.robot.animate(this.animating);
}
//
RobotApp.prototype.onAnimationComplete = function() {
	this.animating = false;
}
//
RobotApp.animation_time = 1111;
//
// Classe Robot específica
//
Robot = function() {
	Sim.Object.call(this);
}
//
Robot.prototype = new Sim.Object();
//
Robot.prototype.init = function() {
	//
    // Fazer um grupo para o robot
    //
	var bodygroup = new THREE.Object3D();
    //
    // Informar o framework sobre este objecto
    //
    this.setObject3D(bodygroup);	
	//
	var that = this;
	// GREAT cartoon robot model - http://www.turbosquid.com/FullPreview/Index.cfm/ID/475463
	// Licensed
	//
	//	Importar um modelo COLLADA
	//
	var url = '../media/robot_cartoon_02/robot_cartoon_02.dae';
	var loader = new THREE.ColladaLoader();
	//
	//	Quando terminar de carregar o ficheiro...
	//
	loader.load(url, function (data) {
		that.handleLoaded(data)
	});
}
//
//	Gerir o modelo importado.
//
Robot.prototype.handleLoaded = function(data) {
	if (data) {
		//
		//	Este modelo é a cena importada
		//
	    var model = data.scene;
	    //
	    // Ajustar a escala
	    //
	    model.scale.set(.01, .01, .01);
		//
	    this.object3D.add(model);
	    //
	    //	Percorrer o modelo e procurar partes específicas.
	    //	(ver a função traverseCallback)
	    //
	    var that = this; // hack no javascript para contornar "this"
	    model.traverse( function (child) {
	    	that.traverseCallback(child);
	    } );
	    //
	    this.createAnimation();
	}	
}
//
//	Registar as partes a animar.
//
//	Quando o modelo é percorrido (traverse) esta função é chamada em cada nó "n". 
//
Robot.prototype.traverseCallback = function(n) {
	switch (n.name) {
		case 'jambe_G' : 			// registar perna esquerda
			this.left_leg = n;
			break;
		case 'jambe_D' : 			// registar perna direita
			this.right_leg = n;
			break;
		case 'head_container' : 	// registar cabeça
			this.head = n;
			break;
		case 'clef' : 				// registar chave
			this.key = n;
			break;
		default :
			break;
	}
}
//
//	Definir chaves e valores para a animação
//
Robot.prototype.createAnimation = function() {
	this.animator = new Sim.KeyFrameAnimator;
	console.log(this.animator);
	this.animator.init({ 
		//
		interps: [
			//
			//	CORPO
			//
		    {	keys: 	Robot.bodyRotationKeys,
		    	values: Robot.bodyRotationValues,
		    	target: this.object3D.rotation }, 
		    //
		    //	CABEÇA
		    //
		    {	keys: 	Robot.headRotationKeys,
		    	values: Robot.headRotationValues,
		    	target: this.head.rotation }, 
		    //
		    //	CHAVE
		    //
		    {	keys: 	Robot.keyRotationKeys,
		    	values: Robot.keyRotationValues,
		    	target: this.key.rotation }, 
		    //
		    //	PERNA ESQ
		    //
		    {	keys: 	Robot.leftLegRotationKeys,
		    	values: Robot.leftLegRotationValues,
		    	target: this.left_leg.rotation }, 
		    //
		    //	PERNA DIR
		    //
		    {	keys: 	Robot.rightLegRotationKeys,
		    	values: Robot.rightLegRotationValues,
		    	target: this.right_leg.rotation },  ],
		//
		loop: true,
		//
		duration: RobotApp.animation_time
		//
	});
	//
	this.animator.subscribe("complete", this, this.onAnimationComplete);
	//
	this.addChild(this.animator);    
}
//
//
//
Robot.prototype.animate = function(on) {
	if (on) {
	    this.animator.start();
	} else {
		this.animator.stop();
	}
}
//
//
//
Robot.prototype.onAnimationComplete = function() {
	this.publish("complete");
}
//
//	chaves/valores para ROTAÇÃO CABEÇA
//
Robot.headRotationKeys = [0, .25, .5, .75, 1];
Robot.headRotationValues = [
	{ z: 0 }, 
    { z: -Math.PI / 96 },
    { z: 0 },
    { z: Math.PI / 96 },
    { z: 0 }, ];
//
//	chaves/valores para ROTAÇÃO CORPO
//
Robot.bodyRotationKeys = [0, .25, .5, .75, 1];
Robot.bodyRotationValues = [
	{ x: 0 }, 
    { x: -Math.PI / 48 },
    { x: 0 },
    { x: Math.PI / 48 },
    { x: 0 }, ];
//
//	chaves/valores para ROTAÇÃO CHAVE (de dar corda)
//
Robot.keyRotationKeys = [0, .25, .5, .75, 1];
Robot.keyRotationValues = [
	{ x: 0 }, 
    { x: Math.PI / 4 },
    { x: Math.PI / 2 },
    { x: Math.PI * 3 / 4 },
    { x: Math.PI }, ];
//
//	chaves/valores para ROTAÇÃO PERNA ESQUERDA
//
Robot.leftLegRotationKeys = [0, .25, .5, .75, 1];
Robot.leftLegRotationValues = [
	{ z: 0 }, 
    { z: Math.PI / 6},
    { z: 0 },
    { z: 0 },
    { z: 0 }, ];
//
//	chaves/valores para ROTAÇÃO PERNA DIREITA
//
Robot.rightLegRotationKeys = [0, .25, .5, .75, 1];
Robot.rightLegRotationValues = [
	{ z: 0 }, 
    { z: 0 },
    { z: 0 },
    { z: Math.PI / 6},
    { z: 0 }, ];


