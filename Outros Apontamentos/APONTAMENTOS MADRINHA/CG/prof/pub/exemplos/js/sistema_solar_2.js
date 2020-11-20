//
// Classe específica para o sistema solar
//
SolarSystemApp = function() {

	Sim.App.call(this);
}
//
//  Subclasse de Sim.App
//
SolarSystemApp.prototype = new Sim.App();
//
//  Inicialização do sistema solar
//
SolarSystemApp.prototype.init = function(container) {
	//
	//	Chamar a superclasse para definir a cena, renderer e câmara
	//
	Sim.App.prototype.init.call(this, container);
    //
    //	Fazer atributos para
    //	- os planetas
    //	- as órbita
    //	- outros "bits"
    //	
	this.planets = [];
	this.orbits = [];
	this.lastX = 0;
	this.lastY = 0;
	this.mouseDown = false;
    //
    // Fazer o sol
    //
    var sun = new Sun();
    sun.init();
    this.addObject(sun);    
    //
    // Fazer o fundo de estrelas
    //
    var stars = new Stars();
    // Posicionar as estrela afastadas
    stars.init(Sun.SIZE_IN_EARTHS + SolarSystemApp.EARTH_DISTANCE * SolarSystemApp.PLUTO_DISTANCE_IN_EARTHS);
    this.addObject(stars);
    //
    // Juntar os planetas
    //
	this.createPlanets();
    //
    // Mover a câmara um pouco para trás, de forma a ver-se o sistema
    //
    this.camera.position.set(0, 0, Sun.SIZE_IN_EARTHS * 8);
    //
    // Fazer uma luz ambiente
    //    
    var amb = new THREE.AmbientLight(0x676767);
    this.scene.add(amb);
    //
    // Inclinar um pouco todo o sistema na direcção da câmara
    //
    this.root.rotation.x = Math.PI / 8;
    //
}
//
//	Gerir as interacções com o rato - movimento
//
SolarSystemApp.prototype.handleMouseMove = function(x, y) {
	if (this.mouseDown) {
		var dx = x - this.lastX;
		if (Math.abs(dx) > SolarSystemApp.MOUSE_MOVE_TOLERANCE) {
			this.root.rotation.y -= (dx * 0.01);
		}
		this.lastX = x;
		return;
		
		var dy = y - this.lastY;
		if (Math.abs(dy) > SolarSystemApp.MOUSE_MOVE_TOLERANCE) {
			this.root.rotation.x += (dy * 0.01);
		    //
		    // Limitar aos limites do sistema solar
		    //
			if (this.root.rotation.x < 0)
				this.root.rotation.x = 0;			
			if (this.root.rotation.x > SolarSystemApp.MAX_ROTATION_X)
				this.root.rotation.x = SolarSystemApp.MAX_ROTATION_X;			
		}
		this.lastY = y;		
	}	
}
//	Gerir as interacções com o rato - pressionar
SolarSystemApp.prototype.handleMouseDown = function(x, y) {
	this.lastX = x;
	this.lastY = y;
	this.mouseDown = true;
}
//	Gerir as interacções com o rato - libertar
SolarSystemApp.prototype.handleMouseUp = function(x, y) {
	this.lastX = x;
	this.lastY = y;
	this.mouseDown = false;
}
//	Gerir as interacções com o rato - scroll
SolarSystemApp.prototype.handleMouseScroll = function(delta) {
	var dx = delta;
	this.camera.position.z -= dx;
    //
    // Limitar aos limites do sistema solar
    //
	if (this.camera.position.z < SolarSystemApp.MIN_CAMERA_Z)
		this.camera.position.z = SolarSystemApp.MIN_CAMERA_Z;
	if (this.camera.position.z > SolarSystemApp.MAX_CAMERA_Z)
		this.camera.position.z = SolarSystemApp.MAX_CAMERA_Z;
}
//
// Fazer os planetas
//
SolarSystemApp.prototype.createPlanets = function() {	
	var i, len = SolarSystemApp.planet_specs.length;
	for (i = 0; i < len; i++) {
		//
		//	Obter parâmetros do planeta
		//
		var spec = SolarSystemApp.planet_specs[i];
		var planet = spec.type ? new spec.type : new Planet;
		//
		//	Inicializar o planeta
		//
		planet.init({
			animateOrbit: 		true,
			animateRotation: 	true,
			showOrbit: 			true,    	
			distance: 			spec.distance * SolarSystemApp.EARTH_DISTANCE + Sun.SIZE_IN_EARTHS, 
	    	size: 				spec.size * SolarSystemApp.EXAGGERATED_PLANET_SCALE, 
	    	period : 			spec.period,
	    	daylen : 			spec.daylen,
	    	revolutionSpeed : 	0.002,
	    	map : 				spec.map });
		//
		//	Juntar planeta a este grupo
		//
		this.addObject(planet);
		//
		//	... e à lista de planetas
		//
		this.planets.push(planet);
		//
		//	Fazer a órbita
		//
		var orbit = new Orbit();
		orbit.init(spec.distance * SolarSystemApp.EARTH_DISTANCE + Sun.SIZE_IN_EARTHS);
		//
		//	Juntar órbita a este grupo
		//
		this.addObject(orbit);		
		//
		//	... e à lista de órbitas
		//
		this.orbits.push(orbit);
	}
}
//
//  Parâmetros do modelo do sistema solar
//
SolarSystemApp.MOUSE_MOVE_TOLERANCE = 4;
SolarSystemApp.MAX_ROTATION_X = Math.PI / 2;
SolarSystemApp.MAX_CAMERA_Z = Sun.SIZE_IN_EARTHS * 50;
SolarSystemApp.MIN_CAMERA_Z = Sun.SIZE_IN_EARTHS * 3;
SolarSystemApp.EARTH_DISTANCE = 50;
SolarSystemApp.PLUTO_DISTANCE_IN_EARTHS = 77.2;
SolarSystemApp.EARTH_DISTANCE_SQUARED = 45000;
SolarSystemApp.EXAGGERATED_PLANET_SCALE = 5.55;
//
//  Parâmetros específicos dos planetas (astronomicamente correctos)
//
SolarSystemApp.planet_specs = [
   {	// Mercury
   		size : 1 / 2.54,	// diam terra
   		distance : 0.4,		// ua
   		period : 0.24,		// anos (da terra)
   		daylen : 59,		// dias (da terra)
   		map : "../media/mercury.jpg" },   
   {	// Venus
   		size : 1 / 1.05,	// diam terra	
   		distance : 0.7,		// ua
   		period : 0.62,		// anos (da terra)
   		daylen : 243,		// dias (da terra)
   		map : "../media/venus.jpg"  },   
   {	// Earth
   		type : Earth, // especial
   		size : 1 ,
   		distance : 1,
   		period : 1,
   		map : "../media/earth_surface.jpg"  },   
   {	// Mars
   		size : 1 / 1.88,	// diam terra
   		distance : 1.6,		// ua
   		period : 1.88,		// anos (da terra)
   		daylen : 1.1,		// dias (da terra)
   		map : "../media/mars.jpg"  },
   {	// Jupiter
   		size : 11.1,	// diam terra
   		distance : 5.2,	// ua
   		period : 11.86, // anos (da terra)
   		daylen : 10.0/24.0,		// dias (da terra)
   		map : "../media/jupiter.jpg"  },   
   {	// Saturn
   		type : Saturn,	// especial
   		size : 9.41,	// diam terra
   		distance : 10,	// ua
   		period : 29.46, // anos (da terra)
   		daylen : 10.7/24.0,		// dias (da terra)
   		map : "../media/saturn.jpg"  },   
   {	// Uranus
   		size : 4,			// diam terra
   		distance : 19.6,	// ua
   		period : 84.01,		// anos (da terra)
   		daylen : 17.0/24.0,		// dias (da terra)
   		map : "../media/uranus.jpg"  },   
   {	// Neptune
   		size : 3.88,		// diam terra
   		distance : 38.8,	// ua
   		period : 164.8,		// anos (da terra)
   		daylen : 16.0/24.0,		// dias (da terra)
   		map : "../media/neptune.jpg"  },
];

