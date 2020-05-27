//
// Classe específica para o sol
//
Sun = function() {
	Sim.Object.call(this);
}
//
//  Subclasse de Sim.Object
//
Sun.prototype = new Sim.Object();
//
//  Inicialização do sol
//
Sun.prototype.init = function() {
    //
    // Fazer um grupo com o sol e iluminação
    //
	var sunGroup = new THREE.Object3D();
	//
	//	Mapas de cores a aplicar ao sol
    //
	var SUNMAP = "../media/sun.jpg";
	var NOISEMAP = "../media/sun_cloud.png";
	//
	//	"uniforms" são valores "read only" para os shaders
	//	mas que podem ser modificado externamente
	//
	var uniforms = {
		time: {
			type: 		"f",
			value: 		1.0 },
		texture1: {
			type: 		"t",
			value: 		THREE.ImageUtils.loadTexture( NOISEMAP  ) },
		texture2: {
			type: 		"t",
			value: 		THREE.ImageUtils.loadTexture( SUNMAP ) } };
	uniforms.texture1.value.wrapS = uniforms.texture1.value.wrapT = THREE.RepeatWrapping;
	uniforms.texture2.value.wrapS = uniforms.texture2.value.wrapT = THREE.RepeatWrapping;
	//
	//	Usar os sharers (no documento html) para definir o material do sol.
	//
	var material;
	if (true) {
		material = new THREE.ShaderMaterial({
			uniforms: 		uniforms,
			wireframe: 		false,
			vertexShader: 	document.getElementById( 'vertexShader' ).textContent,
			fragmentShader: document.getElementById( 'fragmentShader' ).textContent });
	} else {
		material = new THREE.MeshPhongMaterial({
			map: THREE.ImageUtils.loadTexture(NOISEMAP), });
	}
	//
	// Fazer a mesh do sol
	//
    var geometry = new THREE.SphereGeometry(Sun.SIZE_IN_EARTHS, 64, 64);
	sunMesh = new THREE.Mesh( geometry, material );
	//
	//	Guardar "uniforms" para poderem ser animados mais tarde
	//
	this.uniforms = uniforms;
	//
	//	Obter um relógio para guiar a animação
	//
	this.clock = new THREE.Clock();
	//
	//	Fazer um ponto de luz para iluminar o sistema solar
	//
	var light = new THREE.PointLight( 0xffffff, 1.2, 100000 );
    //
    //	Juntar a mesh do sol e a luz ao grupo deste objecto
    //
	sunGroup.add(sunMesh);
	sunGroup.add(light);
	//
    //	Definir o grupo deste objecto
    //
    this.setObject3D(sunGroup);    
}
//
//	Actualização do sol
//
Sun.prototype.update = function() {
	//
	//	Obter tempo decorrido
	//
	var delta = this.clock.getDelta();
	//
	//	Usar o tempo decorrido para as "uniforms"
	//
 	this.uniforms.time.value += 0.5*delta;
 	//
 	//	Evocar o método da superclasse
 	//
	Sim.Object.prototype.update.call(this);
	//
	//	Rodar
	//
	this.object3D.rotation.y -= 0.001;
}
//
//  Parâmetros do sol
//
Sun.SIZE_IN_EARTHS = 100;
