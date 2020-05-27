//
// Construtor
//
TweenApp = function() {
	Sim.App.call(this);
}
//
// Subclasse de Sim.App
//
TweenApp.prototype = new Sim.App();
//
// Inicialização específica
//
TweenApp.prototype.init = function(param) {
	//
	Sim.App.prototype.init.call(this, param);
    //
	var light = new THREE.PointLight( 0xffffff, 1, 100);
	light.position.set(0, 0, 20);
	this.scene.add(light);
	//
	this.camera.position.z = 6.667;
	//
    // Fazer uma MovingBall e acrescentá-la à simulação
    //
    var movingBall = new MovingBall();
    movingBall.init();
    this.addObject(movingBall);
    //
    this.movingBall = movingBall;
}

TweenApp.prototype.update = function() {
	//
	//	Actualizar todos os tweens
	//
    TWEEN.update();
    //
	Sim.App.prototype.update.call(this);
}

TweenApp.prototype.handleMouseUp = function(x, y) {
	this.movingBall.animate();
}
//
// Classe MovingBall específica
//
MovingBall = function() {
	Sim.Object.call(this);
}
//
MovingBall.prototype = new Sim.Object();
//
MovingBall.prototype.init = function() {
	//
    // Fazer esta MovingBall
    //
	var BALL_TEXTURE = "../media/pluto.jpg";
    var geometry = new THREE.SphereGeometry(1, 32, 32);
    var material = new THREE.MeshPhongMaterial( {
    	map: THREE.ImageUtils.loadTexture(BALL_TEXTURE) });
    var mesh = new THREE.Mesh( geometry, material ); 
    mesh.position.x = -3.333;
    //
    // Informar o framework sobre este objecto
    //
    this.setObject3D(mesh);    
}

MovingBall.prototype.animate = function() {
	var newpos;
	if (this.object3D.position.x > 0) {
		newpos = this.object3D.position.x - 6.667;
	} else {
		newpos = this.object3D.position.x + 6.667;
	}
	
	new TWEEN.Tween(this.object3D.position)
    .to( {
        x: newpos
    }, 2000).start();
}

