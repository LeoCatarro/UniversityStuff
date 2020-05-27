//
// Classe específica para a terra
//
Earth = function() {
	Sim.Object.call(this);
}
//
//  Subclasse de Sim.Object
//
Earth.prototype = new Sim.Object();
//
//  Inicialização da terra
//
Earth.prototype.init = function(param) {
	//
	//	param.animateOrbit
	//	param.animateRotation
	//	param.period
	//	param.revolutionSpeed
	//	param.distance
	//	param.size
	//	param.hires
	//	param.showOrbit
	//
	param = param || {};
	//
	//	Passar os parâmetros para atributos desta instância
	//
	this.animateOrbit = param.animateOrbit || false;
	this.animateRotation = param.animateRotation || false;
	this.period = param.period;
	this.revolutionSpeed = param.revolutionSpeed ? param.revolutionSpeed : Earth.ROTATION_Y;
	this.rotationSpeed = this.revolutionSpeed * 365 / 2;
	this.cloudsRotationSpeed = this.rotationSpeed * Earth.CLOUDS_ROTATION_FACTOR;
	//
	//	Fazer um grupo para simular a órbita: este é o topo do grupo da terra
	//
	var earthOrbitGroup = new THREE.Object3D();
    //
    //  Informar a simulação deste objecto
    //
    this.setObject3D(earthOrbitGroup);
    //
    //  Fazer um grupo com as meshes da terra e das nuvens
    //
    var earthGroup = new THREE.Object3D();
    var distance = param.distance || 0;
    var distsquared = distance * distance;
    earthGroup.position.set(Math.sqrt(distsquared/2), 0, -Math.sqrt(distsquared/2));
    earthOrbitGroup.add(earthGroup);
    //
    this.earthGroup = earthGroup;
    var size = param.size || 1;
    this.earthGroup.scale.set(size, size, size);
    //
    //	Juntar o globo da terra e as nuvens
    //
    if (param.hires) {
    	this.createShaderGlobe();
    	this.createClouds();
    } else {
    	this.createLitGlobe();
    }
    //
    //	Juntar a lua
    //
    this.createMoon(size, distance, this.rotationSpeed / Moon.PERIOD);
	//
	if (param.showOrbit) {
		this.createMoonOrbit(distance, size);
	}	
}
//
//  Fazer a terra com aparência mais sofisticada
//
Earth.prototype.createShaderGlobe = function() {  
    //
    //  Fazer a terra com várias texturas: mapas de normais (para as elevações) e de brilhos
    //
    var surfaceMap = THREE.ImageUtils.loadTexture( "../media/earth_surface.jpg" );
    var normalMap = THREE.ImageUtils.loadTexture( "../media/earth_normals.jpg" );
    var specularMap = THREE.ImageUtils.loadTexture( "../media/earth_specular.jpg" );
    //
    //  Definir o material com mapas de cores, normais e brilhos.
    //
    var material = new THREE.MeshPhongMaterial({
        map: surfaceMap,            // mapa de cores
        normalMap: normalMap,       // mapa das normais
        specularMap: specularMap    // mapa dos brilhos
    });
    //
    //  Definir a geometria (uma esfera)
    //
    var globeGeometry = new THREE.SphereGeometry(1, 32, 32);
    //
    //  Juntar a geometria e o material
    //
    var globeMesh = new THREE.Mesh( globeGeometry, material ); 
    //
    //  Calcular as tangentes da geometria (usa o mapa das normais!)
    //
    globeGeometry.computeTangents();
	//
	//	Dar uma pequena inclinação à terra
	//
	globeMesh.rotation.z = Earth.TILT;
	//
	//	Juntar esta mesh ao grupo
	//
	this.earthGroup.add(globeMesh);
	//
	//	Guardar uma referência de forma a podermos aceder-lhe directamente (por exemplo, para rodar)
	//
	this.globeMesh = globeMesh;
}
//
//  Fazer a terra com aparência mais simples
//
Earth.prototype.createLitGlobe = function() {
	//
	//	Fazer a terra com uma textura simples
	//
    var surfaceMap = THREE.ImageUtils.loadTexture("../media/earth_surface.jpg");
    var geometry = new THREE.SphereGeometry(1, 32, 32);
    var material = new THREE.MeshPhongMaterial( {map: surfaceMap} );
    var globeMesh = new THREE.Mesh( geometry, material ); 
	globeMesh.rotation.z = Earth.TILT;
	this.earthGroup.add(globeMesh);
	this.globeMesh = globeMesh;
}
//
//	Fazer as nuvens
//
Earth.prototype.createClouds = function() {	
	//
	// Fazer as nuvens
	//
	var cloudsMap = THREE.ImageUtils.loadTexture( "../media/earth_clouds.png" );
	//
	//	Definir o material (com brilho uniforme)
	//
	var cloudsMaterial = new THREE.MeshLambertMaterial( {
		color: 0xffffff,	// branco
		map: cloudsMap,		// mapa de cores
		transparent: true	// usar o canal de opacidade
	} );
	//
	//	Definir a geometria
	//
	var cloudsGeometry = new THREE.SphereGeometry(Earth.CLOUDS_SCALE, 32, 32);
	//
	//	Juntar a geometria e o material
	//
	cloudsMesh = new THREE.Mesh( cloudsGeometry, cloudsMaterial );
	//
	//	Ajustar a inclinação
	//
	cloudsMesh.rotation.z = Earth.TILT;
	//
	// Juntar as nuvens ao grupo
	//
	this.object3D.add(cloudsMesh);
	//
	// Guardar uma referência de forma a podermos aceder-lhe directamente (por exemplo, para rodar)
	//
	this.cloudsMesh = cloudsMesh;
}
//
//  Fazer a lua
//
Earth.prototype.createMoon = function(size, distance, rotationSpeed) {
	//
	//	Instanciar da classe Moon
	//
	var moon = new Moon();
	//
	//	Inicializar
	//
	moon.init({
		size: 			size,
		distance: 		distance,
		rotationSpeed: 	rotationSpeed
	});
	//
	//	Adicionar como descendente (da terra)
	//
	this.addChild(moon);
	//
	//	Posicionar (estamos no espaço do objecto)
	//
	var distsquared = distance * distance;
	moon.setPosition(Math.sqrt(distsquared/2), 0, -Math.sqrt(distsquared/2));
}

Earth.prototype.createMoonOrbit = function(distance, size) {
	var moonOrbit = new Orbit();
	moonOrbit.init(Moon.DISTANCE_FROM_EARTH / Earth.RADIUS / size);
	this.addChild(moonOrbit);
	var distsquared = distance * distance;
	moonOrbit.setPosition(Math.sqrt(distsquared/2), 0, -Math.sqrt(distsquared/2));
}

Earth.prototype.update = function() {
	// Simulate the orbit
	if (this.animateOrbit) {
		this.object3D.rotation.y += this.revolutionSpeed;
	}
	
	if (this.animateRotation) {
		// "I feel the Earth move..."
		this.globeMesh.rotation.y += this.rotationSpeed;

		// "Clouds, too..."
		if (this.cloudsMesh) {
			this.cloudsMesh.rotation.y += this.cloudsRotationSpeed;
		}
	}
	
	Sim.Object.prototype.update.call(this);
}

Earth.ROTATION_Y = 0.003;
Earth.TILT = 0.41;
Earth.RADIUS = 6371;
Earth.CLOUDS_SCALE = 1.005;
Earth.CLOUDS_ROTATION_FACTOR = 0.95;

// Custom Moon class
Moon = function() {
	Sim.Object.call(this);
}

Moon.prototype = new Sim.Object();

Moon.prototype.init = function(param) {
	param = param || {};
	
	this.rotationSpeed = param.rotationSpeed || Moon.ROTATION_SPEED;
	var size = param.size || 1;
	
	// Create a group to contain the Moon and orbit
	var moonGroup = new THREE.Object3D();

	var MOONMAP = "../media/moon.jpg";	
	var geometry = new THREE.SphereGeometry(Moon.SIZE_IN_EARTHS * size, 32, 32);
	var texture = THREE.ImageUtils.loadTexture(MOONMAP);
	var material = new THREE.MeshPhongMaterial( {
		map: texture, 
		ambient : 0x888888
	});
	var mesh = new THREE.Mesh( geometry, material );    
	var distance = Moon.DISTANCE_FROM_EARTH / Earth.RADIUS / size;
	var distsquared = distance * distance;
	mesh.position.set(Math.sqrt(distsquared/2), 0, -Math.sqrt(distsquared/2));
	moonGroup.add(mesh);

    // Tell the framework about our object
    this.setObject3D(moonGroup);
}

Moon.prototype.update = function() {
	// Moon orbit
	this.object3D.rotation.y += this.rotationSpeed;
	
	Sim.Object.prototype.update.call(this);
}

Moon.DISTANCE_FROM_EARTH = 356400;
Moon.PERIOD = 28;
Moon.ROTATION_SPEED = 0.003;
Moon.EXAGGERATE_FACTOR = 1.2;
Moon.SIZE_IN_EARTHS = 1.0 / 3.7 * Moon.EXAGGERATE_FACTOR;
