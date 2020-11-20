var camera, scene, renderer;
var geometry, material, mesh;

init();
animate();

function init() {

    camera = new THREE.PerspectiveCamera( 70, window.innerWidth / window.innerHeight, 0.01, 10 );
    camera.position.z = 1;

    scene = new THREE.Scene();

    geometry = new THREE.BoxGeometry( 0.2, 0.2, 0.2 );
    material = new THREE.MeshNormalMaterial();

    mesh = new THREE.Mesh( geometry, material );
    scene.add( mesh );
		
		var targetPosition1 = new THREE.Vector3( 0.5, 0, 0 );
		var targetPosition2 = new THREE.Vector3( 0.5, 0.5, 0 );
		var targetPosition3 = new THREE.Vector3( 0, 0.5, 0 );
        
        mesh.position.set(-5, 0, 0);  // Change Mesh Position

		var tween1 = new TWEEN.Tween( mesh.position ).to( targetPosition1, 1000 ); 
		var tween2 = new TWEEN.Tween( mesh.position ).to( targetPosition2, 1000 ); 
		var tween3 = new TWEEN.Tween( mesh.position ).to( targetPosition3, 1000 );
		
		tween1.chain( tween2 );
		tween2.chain( tween3 );
		
		tween1.start();

    renderer = new THREE.WebGLRenderer( { antialias: true } );
    renderer.setSize( window.innerWidth, window.innerHeight );
    document.body.appendChild( renderer.domElement );

}

function animate( time ) {

    requestAnimationFrame( animate );
		
	TWEEN.update( time );
		
    renderer.render( scene, camera );

}