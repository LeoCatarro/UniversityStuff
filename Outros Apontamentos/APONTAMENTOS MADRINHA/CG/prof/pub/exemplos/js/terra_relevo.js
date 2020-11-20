//
// Classe específica para a aplicação
//
EarthApp = function() {	
	Sim.App.call(this);
}
//
// Subclasse de Sim.App
//
EarthApp.prototype = new Sim.App();
//
// Inicialização da aplicação
//
EarthApp.prototype.init = function(param) {	
	//
	// Inicialização da cena, renderer e câmara: na super-classe
	//
	Sim.App.prototype.init.call(this, param);
	//
	// Fazer a terra e juntá-la à simulação
	//
	var earth = new Earth();
	earth.init();
	this.addObject(earth);
	//
	// Fazer uma fonte de iluminação
	//
	var sun = new Sun();
	sun.init();
	this.addObject(sun);
}
//
// Classe específica para a terra
//
Earth = function() {	
	Sim.Object.call(this);
}
//
//	Subclasse de Sim.Object
//
Earth.prototype = new Sim.Object();
//
//	Inicialização da terra
//
Earth.prototype.init = function() {	
	//
	// Fazer um grupo com a terra e as nuvens
	//
	var earthGroup = new THREE.Object3D();
	//
	// Informar a simulação deste objecto
	//
	this.setObject3D(earthGroup);
	//
	// Juntar a terra
	//
	this.createGlobe();
	//
	//	... e as nuvens
	//
	this.createClouds();
}
//
//	Fazer a terra
//
Earth.prototype.createGlobe = function() {	
	//
	//	Fazer a terra com várias texturas: mapas de normais (para as elevações) e de brilhos
	//
	var surfaceMap = THREE.ImageUtils.loadTexture( "../media/earth_surface.jpg" );
	var normalMap = THREE.ImageUtils.loadTexture( "../media/earth_normals.jpg" );
	var specularMap = THREE.ImageUtils.loadTexture( "../media/earth_specular.jpg" );
	//
	//	Definir o material com mapas de cores, normais e brilhos.
	//
	var material = new THREE.MeshPhongMaterial({
		map: surfaceMap,			// mapa de cores
		normalMap: normalMap,		// mapa das normais
		specularMap: specularMap	// mapa dos brilhos
	});
	//
	//	Definir a geometria (uma esfera)
	//
	var globeGeometry = new THREE.SphereGeometry(1, 32, 32);
	//
	//	Juntar a geometria e o material
	//
	var globeMesh = new THREE.Mesh( globeGeometry, material );
    //
    //  Calcular as tangentes da geometria (usando o mapa das normais!)
    //
    //globeGeometry.computeTangents();
	//
	//	Dar uma pequena inclinação à terra
	//
	//globeMesh.rotation.x = Earth.TILT;
	//
	//	Juntar esta mesh ao grupo
	//
	this.object3D.add(globeMesh);
	//
	//	Guardar uma referência de forma a podermos aceder-lhe directamente (por exemplo, para rodar)
	//
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
	cloudsMesh.rotation.x = Earth.TILT;
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
//	Mover a terra e as nuvens
//
Earth.prototype.update = function() {	
	//
	// Rodar a terra
	//
	this.globeMesh.rotation.y += Earth.ROTATION_Y;
	//
	// ... e as nuvens
	//
	this.cloudsMesh.rotation.y += Earth.CLOUDS_ROTATION_Y;
	//
	//	Chamar o método da super-classe
	//
	Sim.Object.prototype.update.call(this);
}
//
//	Parâmetros do modelo da terra com as nuvens
//
Earth.ROTATION_Y = 0.005;	// velocidade de rotação da terra
Earth.TILT = 0.41;			// inclinação da terra
Earth.CLOUDS_SCALE = 1.005;	// tamanho das nuvens (em relação à terra)
Earth.CLOUDS_ROTATION_Y = Earth.ROTATION_Y * 1.02; // velocidade de rotação das nuvens
//
// Classe específica para o Sol
//
Sun = function() {	
	Sim.Object.call(this);
}
//
//	Subclasse de Sim.Object
//
Sun.prototype = new Sim.Object();
//
//	Inicialização do Sol
//
Sun.prototype.init = function() {	
	//
	// Fazer um ponto de luz para mostrar a terra
	//
	var light = new THREE.PointLight( 0xffffff, 2, 100);
	//
	// Posicionar ligeiramente atrás e à esquerda
	//
	light.position.set(-10, 0, 20);
	//
	// Informar a simulação deste objecto
	//
	this.setObject3D(light);
}