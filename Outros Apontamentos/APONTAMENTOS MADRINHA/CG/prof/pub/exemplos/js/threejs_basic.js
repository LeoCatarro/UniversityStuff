////////////////////////////////////////////////////////////
//
//  CRIAR UM "CONTEXTO" THREEJS
//
////////////////////////////////////////////////////////////
/**
    Criar um objecto simples para trabalhar com o Three.js
    
    O argumento "containerId" identifica o elemento do documento
        onde vai ser colocado o modelo 3D

    O resultado é um objecto com os seguintes atributos

        .renderer   : um THREE.WebGLRenderer criado no elemento "containerId";
        .scene      : uma THREE.Scene
        .camera     : uma THREE.Camera

    Em .scene é acrescentada uma luz direccional.
*/
function makeContext3JS(containerId) {
    var renderer = null,
    scene = null,
    camera = null;
    //
    var container = document.getElementById(containerId);
    //
    // Criar o renderer Three.js e juntá-lo ao <div>
    //
    renderer = new THREE.WebGLRenderer( { antialias: true } );
    renderer.setSize(container.offsetWidth, container.offsetHeight);
    container.appendChild( renderer.domElement );
    //
    // Criar uma cena
    //
    scene = new THREE.Scene();
    //
    // Colocar uma câmara
    //
    camera = new THREE.PerspectiveCamera(
        45,
        container.offsetWidth / container.offsetHeight, 1, 4000 );
    camera.position.set( 0, 0, 3 );
    //
    // Criar uma luz direccional para se ver os objectos
    //
    var light = new THREE.DirectionalLight( 0xffffcc, 1.5);
    light.position.set(0, 1, 1);
    scene.add( light );
    //
    return {
        renderer: renderer,
        scene: scene,
        camera: camera,
    };
}
////////////////////////////////////////////////////////////
//
//	ANIMAÇÃO
//
////////////////////////////////////////////////////////////
/**
    Fazer um método simples de animação.

    O argumento "ctx" deve ter sido criado com "makeContext3JS"

    Se "ctx" tiver um método .update, esse método é corrido com argumento ctx
    (desta forma é mais simples "modularizar" a actualização)
*/
function run(ctx) {
    if (ctx.update) {
        ctx.update(ctx);
    }
	//
    // Desenhar a cena
    //
    ctx.renderer.render( ctx.scene, ctx.camera );
    //
    // Solicitar a próxima imagem da animação
    //
    window.requestAnimationFrame(function() {run(ctx);});
}