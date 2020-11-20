//
// Construtor MaterialApp específico
//
MaterialApp = function() {
	Sim.App.call(this);
}
//
// Subclasse de Sim.App
//
MaterialApp.prototype = new Sim.App();
//
// Inicialização específica
//
MaterialApp.prototype.init = function(param) {
	//
	Sim.App.prototype.init.call(this, param);
	//
	var ambientLight = new THREE.AmbientLight( 0x444444 );
	this.scene.add(ambientLight);
	//
	var directionalLight = new THREE.DirectionalLight(0xffffff, 1);
	directionalLight.position.set(-.3333, 1, .777).normalize();
	this.scene.add( directionalLight );
	this.directionalLight = directionalLight;
	//
	this.camera.position.set(0, 3.333, 6.667);
	this.camera.lookAt(this.root.position);
	//
	this.createGrid();
	this.createObjects();
	this.createAnimations();
}
//
//	Uma grelha para representar o chão
//
MaterialApp.prototype.createGrid = function() {
	//
	var line_material = new THREE.LineBasicMaterial( { color: 0xaaaaaa, opacity: 0.8 } ),
		geometry = new THREE.Geometry(),
		floor = -2, step = 1, size = 66;
	//
	for ( var i = 0; i <= size / step * 2; i ++ ) {
		geometry.vertices.push( new THREE.Vector3( - size, floor, i * step - size ) );
		geometry.vertices.push( new THREE.Vector3(   size, floor, i * step - size ) );	
		geometry.vertices.push( new THREE.Vector3( i * step - size, floor, -size ) );
		geometry.vertices.push( new THREE.Vector3( i * step - size, floor,  size ) );
	}
	//
	var grid = new THREE.Line( geometry, line_material, THREE.LinePieces );
	this.root.add(grid);
}
//
//	Os objectos que vão ser animados
//
MaterialApp.prototype.createObjects = function() {
	//
	//	uma esfera
	//
	var geometry = new THREE.SphereGeometry(1, 32, 32);
	this.ballmaterial = new THREE.MeshPhongMaterial({ color : 0xff0000, ambient : 0x222222,
		transparent : true});
	this.ball = new THREE.Mesh(geometry, this.ballmaterial);
	this.ball.position.set(-2.67, 0, 0);
	//
	this.root.add(this.ball);
	//
	//	um cubo
	//
	var len = Math.sqrt(2);
	geometry = new THREE.BoxGeometry(2, 2, 2);
	this.cubematerial = new THREE.MeshPhongMaterial({ color : 0x0055ff });
	this.cube = new THREE.Mesh(geometry, this.cubematerial);
	this.cube.position.set(0, 0, -5);
	//
	this.root.add(this.cube);
	//
	//	um cilindro
	//
	geometry = new THREE.CylinderGeometry(1, 1, 2, 32);
	this.cylindermaterial = new THREE.MeshPhongMaterial({ color : 0xcccccc, shininess : 1,
		specular : 0xffff00 });
	this.cylinder = new THREE.Mesh(geometry, this.cylindermaterial);
	this.cylinder.position.set(2.67, 0, 0);
	//
	this.root.add(this.cylinder);
	//
}

MaterialApp.prototype.createAnimations = function() {
	//
	//	animação de transparência
	//
	this.transparencyAnimator = new Sim.KeyFrameAnimator;
	this.transparencyAnimator.init({ 
		interps:  	[ { 
	    	keys: 		[0, .5, 1],			// chaves 
	    	values: 	[					// valores
    	        { opacity : 1},
    	        { opacity : 0},
    	        { opacity : 1}, ],
	    	target: 	this.ballmaterial	// alvo
	    }, ],
		loop:  		true,
		duration: 	MaterialApp.animation_time
	});
	//
	this.addObject(this.transparencyAnimator);
	//
	//	animação de cor
	//
	this.colorAnimator = new Sim.KeyFrameAnimator;
	this.colorAnimator.init({ 
		interps: 	[ { 
	    	keys: 		[0, .5, 1], 			// chaves
	    	values: 	[						// valores
    	        { r : 0, g : .333, b : 1 },
    	        { r : 0, g : 1, b : .333 },
    	        { r : 0, g : .333, b : 1 }, ],
	    	target: 	this.cubematerial.color	// alvo
		}, ],
		loop: 		true,
		duration: 	MaterialApp.animation_time
	});
	//
	this.addObject(this.colorAnimator);  
	//
	//	animação de brilho
	//
	this.specularAnimator = new Sim.KeyFrameAnimator;
	this.specularAnimator.init({ 
		interps: 	[ { 
	    	keys: 		[0, .5, 1], 	// chaves
	    	values: 	[				// valores
    	        { r : 1, g : 1 },
    	        { r : 0, g : 0 },
    	        { r : 1, g : 1 }, ],
	    	target: 	this.cylindermaterial.specular	// alvo
	    }, ],
		loop: true,
		duration:MaterialApp.animation_time
	});
	//
	this.addObject(this.specularAnimator);    
}
//
MaterialApp.prototype.animate = function(animator, on) {
	if (on) {
		animator.start();
	} else {
		animator.stop();
	}
}
//
MaterialApp.prototype.setAnimateTransparency = function(val) {
	this.animate(this.transparencyAnimator, val);
}
//
MaterialApp.prototype.setAnimateColor = function(val) {
	this.animate(this.colorAnimator, val);
}
//
MaterialApp.prototype.setAnimateSpecular = function(val) {
	this.animate(this.specularAnimator, val);
}
//
MaterialApp.animation_time = 4444;

