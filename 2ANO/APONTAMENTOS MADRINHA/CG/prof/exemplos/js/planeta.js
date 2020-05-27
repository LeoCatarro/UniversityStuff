//
// Classe genérica para os planetas
//
Planet = function() {
	Sim.Object.call(this);
}
//
// Subclasse de Sim.Object
//
Planet.prototype = new Sim.Object();
//
// Inicialização do planeta
//
Planet.prototype.init = function(param) {
    //
    //  param.distance          distância do centro do sistema
    //  param.size              tamanho do planeta
    //  param.map               mapa de cores
    //  param.animateOrbit      animar a órbita?
    //  param.period            periodo de rotação em torno do centro do sistema
    //  param.revolutionSpeed   velocidade de rotação em torno do centro do sistema
    //
	param = param || {};
	//
    //  Fazer um grupo com uma órbita e o planeta: este é o topo do grupo
    //
    var planetOrbitGroup = new THREE.Object3D();
    //
    //  Informar a simulação deste objecto
    //
    this.setObject3D(planetOrbitGroup);
    //
    //  Fazer um grupo com as meshes do planeta e das nuvens
    //
    var planetGroup = new THREE.Object3D();
    var distance = param.distance || 0;
    var distsquared = distance * distance;
    planetGroup.position.set(Math.sqrt(distsquared/2), 0, -Math.sqrt(distsquared/2));
    planetOrbitGroup.add(planetGroup);
    //
    this.planetGroup = planetGroup;
    var size = param.size || 1;
    this.planetGroup.scale.set(size, size, size);
    //
    var map = param.map;
	this.createGlobe(map);
    //
	this.animateOrbit = param.animateOrbit;
    this.animateRotation = param.animateRotation;
    this.showOrbit = param.showOrbit;
	this.period = param.period;
    this.daylen = param.daylen;
	this.revolutionSpeed = param.revolutionSpeed ? param.revolutionSpeed : Planet.REVOLUTION_Y;    
    this.rotationSpeed = this.daylen;
}
//
//  Fazer a mesh com:
//      - a geometria (uma esfera) e
//      - a aparência definida por um mapa de cores
//
Planet.prototype.createGlobe = function(map) {
    //
    //  Fazer o planeta com um mapa de cores
    //
    var geometry = new THREE.SphereGeometry(1, 32, 32);
    var texture = THREE.ImageUtils.loadTexture(map);
    var material = new THREE.MeshPhongMaterial( {map: texture, ambient: 0x333333} );
    var globeMesh = new THREE.Mesh( geometry, material ); 
    //
    // Juntar a mesh ao grupo do planeta
    //
    this.planetGroup.add(globeMesh);
	//
    // Guardar uma referência de forma a podermos aceder-lhe directamente (por exemplo, para rodar)
    //
    this.globeMesh = globeMesh;
}
//
//  Mover o planeta
//
Planet.prototype.update = function() {	
    //
    //  Simular a órbita (em torno do sol): anos
    //
	if (this.animateOrbit) 	{
		this.object3D.rotation.y += this.revolutionSpeed / this.period;
	}	
    //
    //  Simular a rotação (em torno do eixo): dias
    //
    if (this.animateRotation)  {
        this.planetGroup.rotation.y += this.daylen;
    }   
    //
    //  Chamar o método da superclasse
    //
	Sim.Object.prototype.update.call(this);
}
//
//  Parâmetros do planeta
//
Planet.REVOLUTION_Y = 0.003;
