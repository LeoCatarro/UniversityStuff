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
    //
    // Mover a câmara um pouco para trás, de forma a ver-se a lua
    //
    this.camera.position.z += 1.667;
}
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
    //  Fazer a terra
    //
    this.createGlobe();
    //
    //  ... as nuvens
    //
    this.createClouds();
    //
    //  ... e a lua
    //
    this.createMoon();
}
//
//  Fazer a terra
//
Earth.prototype.createGlobe = function() {  
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
    //  Calcular as tangentes da geometria (usando o mapa das normais!)
    //
    globeGeometry.computeTangents();
    //
    //  Dar uma pequena inclinação à terra
    //
    globeMesh.rotation.x = Earth.TILT;
    //
    //  Juntar esta mesh ao grupo
    //
    this.object3D.add(globeMesh);
    //
    //  Guardar uma referência de forma a podermos aceder-lhe directamente (por exemplo, para rodar)
    //
    this.globeMesh = globeMesh;
}
//
//  Fazer as nuvens
//
Earth.prototype.createClouds = function() { 
    //
    // Fazer as nuvens
    //
    var cloudsMap = THREE.ImageUtils.loadTexture( "../media/earth_clouds.png" );
    //
    //  Definir o material (com brilho uniforme)
    //
    var cloudsMaterial = new THREE.MeshLambertMaterial( {
        color: 0xffffff,    // branco
        map: cloudsMap,     // mapa de cores
        transparent: true   // usar o canal de opacidade
    } );
    //
    //  Definir a geometria
    //
    var cloudsGeometry = new THREE.SphereGeometry(Earth.CLOUDS_SCALE, 32, 32);
    //
    //  Juntar a geometria e o material
    //
    cloudsMesh = new THREE.Mesh( cloudsGeometry, cloudsMaterial );
    //
    //  Ajustar a inclinação
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
//  Fazer a lua
//
Earth.prototype.createMoon = function() {	
	var moon = new Moon();
	moon.init();
	this.addChild(moon);
}
//
//  Mover a terra e as nuvens
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
    //  Chamar o método da super-classe
    //
    Sim.Object.prototype.update.call(this);
}
//
//  Parâmetros do modelo da terra com as nuvens
//
Earth.ROTATION_Y = 0.01;   // velocidade de rotação da terra
Earth.TILT = 0.41;          // inclinação da terra
Earth.CLOUDS_SCALE = 1.005; // tamanho das nuvens (em relação à terra)
Earth.CLOUDS_ROTATION_Y = Earth.ROTATION_Y * 0.75; // velocidade de rotação das nuvens
Earth.RADIUS = 6371;        // raio da terra, em kilómetros
//
// Classe específica para o Sol
//
Sun = function() {  
    Sim.Object.call(this);
}
//
//  Subclasse de Sim.Object
//
Sun.prototype = new Sim.Object();
//
//  Inicialização do Sol
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
//
// Classe específica para a lua
//
Moon = function() {	
	Sim.Object.call(this);
}
//
//  Subclasse de Sim.Object
//
Moon.prototype = new Sim.Object();
//
//  Inicialização da lua
//
Moon.prototype.init = function() {
    //
    //  Definir a geometria (uma esfera)
    //
    var geometry = new THREE.SphereGeometry(Moon.SIZE_IN_EARTHS, 32, 32);
    //
    //  Fazer a lua com um mapa de cores
    //
    var MOONMAP = "../media/moon.jpg";  
    var texture = THREE.ImageUtils.loadTexture(MOONMAP);
    //
    //  Definir o material com o mapa de cores
    //
    var material = new THREE.MeshPhongMaterial({
        map: texture, 
    	ambient:0x888888
    });
    //
    //  Juntar a geometria e o material
    //
    var mesh = new THREE.Mesh( geometry, material );
    //
    //  Trabalhar em unidades "terra" (a terra é uma esfera de raio 1)
    //
    var distance = Moon.DISTANCE_FROM_EARTH / Earth.RADIUS;
    mesh.position.set(Math.sqrt(distance / 2), 0, -Math.sqrt(distance / 2));
    //
    //  Rodar a lua de forma que mostre a mesma face à terra
    //
    mesh.rotation.y = Math.PI;
    //
    //  Fazer um grupo com a terra e a lua
    //
    var moonGroup = new THREE.Object3D();
    moonGroup.add(mesh);
    //
    //  Inclinar a lua
    //
    moonGroup.rotation.x = Moon.INCLINATION;
    //
    // Informar a simulação deste objecto
    //
    this.setObject3D(moonGroup);
    //
    // Guardar uma referência de forma a podermos aceder-lhe directamente (por exemplo, para rodar)
    //
    this.moonMesh = mesh;
}
//
//  Mover a lua
//
Moon.prototype.update = function() {	
	//
    //  Órbita da lua
    //
	this.object3D.rotation.y += (Earth.ROTATION_Y / Moon.PERIOD);
    //
    //  Chamar o método da super-classe
    //
	Sim.Object.prototype.update.call(this);
}
//
//  Parâmetros do modelo da lua
//
Moon.DISTANCE_FROM_EARTH = 356400;      // distância à terra, em kilómetros
Moon.PERIOD = 2.8;                       // período de rotação da lua em torno da terra
Moon.EXAGGERATE_FACTOR = 1.0;           // aumento para efeito de visualização
Moon.INCLINATION = 0.089;               // inclinação do eixo de rotação da lua
Moon.SIZE_IN_EARTHS = 1 / 3.7 * Moon.EXAGGERATE_FACTOR;  // tamanho da lua em relação ao tamanho da terra
