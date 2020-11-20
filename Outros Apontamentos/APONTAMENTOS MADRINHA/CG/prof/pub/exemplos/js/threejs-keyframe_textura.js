//
// Construtor TextureAnimationApp específico
//
TextureAnimationApp = function() {
	Sim.App.call(this);
}
//
TextureAnimationApp.prototype = new Sim.App();
//
TextureAnimationApp.prototype.init = function(param) {
	//
	Sim.App.prototype.init.call(this, param);	
	// Some scene lighting
	var ambientLight = new THREE.AmbientLight( 0x222222 );
	this.scene.add( ambientLight );
	//
	this.camera.position.z = 10;	
    // Fazer a cascata
    var waterfall = new Waterfall();
    waterfall.init();
    this.addObject(waterfall);
    this.waterfall = waterfall;
    //
    this.waterfall.subscribe("complete", this, this.onAnimationComplete)
}
//
TextureAnimationApp.prototype.handleMouseUp = function(x, y) {
	this.animating = !this.animating;
	this.waterfall.animate(this.animating);
}
//
TextureAnimationApp.prototype.onHeadLightAnimationComplete = function() {
	this.animating = false;
}
//
// Classe para a cascata
//
Waterfall = function() {
	Sim.Object.call(this);
}
//
Waterfall.prototype = new Sim.Object();
//
Waterfall.prototype.init = function() {
	var group = new THREE.Object3D;
	//
	//	Copyright a quem é devido
	//
	// Water texture by Patrick Hoesly
	//
	// http://www.flickr.com/photos/zooboing/
	// http://www.flickr.com/photos/zooboing/4441454031/sizes/o/in/photostream/
	//
	// Attribution 2.0 Generic (CC BY 2.0) 
	//
	var map = THREE.ImageUtils.loadTexture("../media/tex_agua.jpg");
    map.wrapS = map.wrapT = true;
    var material = new THREE.MeshBasicMaterial({
    	color: 0x80aaaa,
    	opacity: .6,
    	transparent: true,
    	map : map
    });
	//
	var geometry = new THREE.PlaneBufferGeometry(2, 6);
    var mesh = new THREE.Mesh(geometry, material);
    //
    group.add(mesh);
    //
    //	Ravina
    //
    var cliffmap = THREE.ImageUtils.loadTexture("../media/tex_rocha.jpg");
    cliffmap.repeat.set(4,3);
    cliffmap.wrapS = cliffmap.wrapT = true;
    var material = new THREE.MeshBasicMaterial({
    	color: 0xaaaaaa,
    	opacity: 1,
    	transparent: false,
    	map : cliffmap
    });
	//
	var geometry = new THREE.PlaneBufferGeometry(5, 6);
    var mesh = new THREE.Mesh(geometry, material);
    //
    //	Afastar um pouco a ravina
    //
    mesh.position.z = -0.5;
    //
    group.add(mesh);
    //
    //	Inclinar um pouco o grupo
    //
    group.rotation.x = -Math.PI / 12;
    //
    //	Guardar a textura que vai ser animada
    //
    this.texture = map;
    //
    this.setObject3D(group);
    //
    //	Fazer a animação
    //
    this.createAnimation();
}
//
//	Fazer a animação da textura
//
Waterfall.prototype.createAnimation = function() {
	this.animator = new Sim.KeyFrameAnimator;
	this.animator.init({ 
		interps: 	[ { 
	    	keys: 		[0, 1], // chaves
	    	values: 	[		// valores
    	        { y: 0},
    	        { y: 1}, ],
	    	target: 	this.texture.offset	// alvo: ajuste da textura
	    }, ],
		loop: 		true,
		duration: 	Waterfall.animation_time
	});
	
	this.addChild(this.animator);    
}

Waterfall.prototype.animate = function(on) {
	if (on) {
	   this.animator.start();
	} else {
		this.animator.stop();
	}
}

Waterfall.animation_time = 4200;
