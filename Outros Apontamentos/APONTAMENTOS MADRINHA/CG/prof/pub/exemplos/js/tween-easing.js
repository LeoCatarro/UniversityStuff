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
	this.camera.position.z = 13;
	//
    // Fazer uma MovingBall e acrescentá-la à simulação
    //
    var movingBall = new MovingBall();
    movingBall.init();
    this.addObject(movingBall);
    //
    this.movingBall = movingBall;
}
//
TweenApp.prototype.update = function() {
	//
	//	Actualizar todos os tweens
	//
    TWEEN.update();
    //
	Sim.App.prototype.update.call(this);
}
//
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
    mesh.position.y = 3.333;
    //
    // Informar o framework sobre este objecto
    //
    this.setObject3D(mesh);    
}
//
MovingBall.prototype.animate = function() {	
	var newpos, easefn;
	if (this.object3D.position.y > 0) 	{
		newpos = this.object3D.position.y - 6.667;
		easefn = MovingBall.useBounceFunction ? 
			TWEEN.Easing.Bounce.Out :
			TWEEN.Easing.Quadratic.Out;
	} else {
		newpos = this.object3D.position.y + 6.667;
		easefn = MovingBall.useBounceFunction ? 
			TWEEN.Easing.Bounce.In :
			TWEEN.Easing.Quadratic.In;
	}	
	new TWEEN.Tween(this.object3D.position)
    .to( {
        y: newpos
    }, 2000)
    .easing(easefn).start();
}
//
//	Usado nos scripts do documento html para receber a opção dos controlos
//
MovingBall.useBounceFunction = false;

