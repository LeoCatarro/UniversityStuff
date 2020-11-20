function setRaycasterModel(ctx) {	
    ////////////////////////////////////////////////////////////
    //
    // Criar alguns objectos
    //
    ////////////////////////////////////////////////////////////
    //
    // Primeiro, obter o mapa da textura
    //
    var mapUrl = "../media/pomba.png";
    var map = THREE.ImageUtils.loadTexture(mapUrl);
    var material = new THREE.MeshPhongMaterial({ map: map });
    var cubeGeometry = new THREE.BoxGeometry(1, 1, 1);
    //
    //	Fazer quatro cubos "em grelha"
    //
    cube1 = new THREE.Mesh(cubeGeometry, material);
    cube1.rotation.x = Math.PI / 5;
    cube1.rotation.y = Math.PI / 5;
    cube1.position.x = -1.0;
    //
    cube2 = new THREE.Mesh(cubeGeometry, material);
    cube2.rotation.x = Math.PI / 5;
    cube2.rotation.y = Math.PI / 5;
    cube2.position.x = +1.0;
    //
    cube3 = new THREE.Mesh(cubeGeometry, material);
    cube3.rotation.x = Math.PI / 5;
    cube3.rotation.y = Math.PI / 5;
    cube3.position.x = -1.0;
    cube3.position.z = -2.0;
    //
    cube4 = new THREE.Mesh(cubeGeometry, material);
    cube4.rotation.x = Math.PI / 5;
    cube4.rotation.y = Math.PI / 5;
    cube4.position.x = +1.0;
    cube4.position.z = -2.0;
    //
    // Juntar os quatro cubos à cena
    //
    ctx.scene.add( cube1 );
    ctx.scene.add( cube2 );
    ctx.scene.add( cube3 );
    ctx.scene.add( cube4 );
}

function updateRaycaster(ctx) {
	//
	//	Usar "ctx.raycaster" para detectar intersecções.
	//
	ctx.raycaster.setFromCamera( ctx.mouse, ctx.camera );
	var intersects = ctx.raycaster.intersectObjects( ctx.scene.children );
	//
	//	Animar os objectos detectados
	//
	for (var i in intersects) {
		intersects[i].object.rotation.y -= 0.01;
	}
	//
	//	Observando cuidadosamente o resultado nota-se que
	//	estas intersecções não são geometricamente exactas.
	//
	//	A precisão das intersecções é parametrizada com
	//	vários atributos de Raycaster --- ver a documentação!
	//
}

/**
	Esta função devolve outra **função**.
	É a segunda função que vai gerir os eventos.

	Este exemplo ilustra uma técnica simples para "embrulhar" o objecto "ctx"
	no contexto da função que vai ser devolvida.
*/
function onMouseMove(ctx) {
	//
	//	Calcular coordenadas do apontador no referencial do viewport
	//
	//	Quando a função seguinte for evocada o valor interno "ctx" é
	//	o argumento "ctx" **desta** função.
	//
	return function(event) {
		ctx.mouse.x = 2*(event.clientX /window.innerWidth) - 1;
		ctx.mouse.y = 2*(event.clientY /window.innerHeight) - 1;
	}
}