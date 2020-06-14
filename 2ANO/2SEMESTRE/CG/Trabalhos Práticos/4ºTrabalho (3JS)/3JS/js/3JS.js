/*

    RENDERER

*/
var renderer = new THREE.WebGLRenderer();
renderer.setSize( window.innerWidth, window.innerHeight );
document.body.appendChild( renderer.domElement );

/*

    CAMERA

*/
var camera = new THREE.PerspectiveCamera( 45, window.innerWidth / window.innerHeight, 1, 500 );
camera.position.set( 0, 0, 100 );
camera.lookAt( 0, 0, 0 );

/*

    CENA

*/
var scene = new THREE.Scene();

/*

    MODELO 3D DA PALAVRA : Canelado

*/
//Letra: C    ALTURA=27   LARGURA=23

var path = new THREE.Path();
    path.moveTo(1,2);
    path.lineTo(3,3.5);
    path.lineTo(-3,7);
    path.quadraticCurveTo(-20,-5,-3,-20);
    path.quadraticCurveTo(2,-17,3,-15);

    path.quadraticCurveTo(-5,-18,-10,-5);
    path.quadraticCurveTo(-10,0,-4,5);
    path.lineTo(1,2);
    
var points = path.getPoints();

var geometry = new THREE.BufferGeometry().setFromPoints( points );
var material = new THREE.LineBasicMaterial( { color: 0xffffff } );

var line = new THREE.Line( geometry, material );
line.translateX(-22); //Translate letter C -20 units on X axis
line.translateY(20);
scene.add( line );
renderer.render( scene, camera );



//Letra: A      ALTURA = 20 LARGURA= 20
var path = new THREE.Path();
    path.moveTo(0,0);
    path.quadraticCurveTo(0.5,-2,3,-3);
    path.lineTo(5,-1);
    path.quadraticCurveTo(3,-1,3,0);
    path.lineTo(3,13);
    path.lineTo(4,14);
    path.lineTo(-2,17);  //(-2,-3)
    path.quadraticCurveTo(-8,15,-9,14);
    path.lineTo(-8,13);
    path.lineTo(-5,15);
    path.lineTo(0,14);
    path.lineTo(0,0);

    path.moveTo(0,10);
    path.quadraticCurveTo(-17,5,-6,-3);
    path.quadraticCurveTo(-2,-2.5,0,0);
    path.lineTo(0,2);
    path.bezierCurveTo(-10,-4,-10,8,0,8);
    
var points = path.getPoints();

var geometry = new THREE.BufferGeometry().setFromPoints( points );
var material = new THREE.LineBasicMaterial( { color: 0xffffff } );

var line = new THREE.Line( geometry, material );
line.translateX(-6);
line.translateY(3);

scene.add( line );
renderer.render( scene, camera );



//Letra: N   ALTURA=20 LARGURA = 20
var path = new THREE.Path();
    path.moveTo(2,0);
    //path.lineTo(2,2);
    path.quadraticCurveTo(0.5,1,0,4);
    path.lineTo(0,14);
    path.quadraticCurveTo(-3,16,-6,14);
    path.lineTo(-6,4);
    path.quadraticCurveTo(-5,1,-4,2);
    path.lineTo(-6,0);
    path.quadraticCurveTo(-8,2,-8,4);
    path.lineTo(-8,14);
    path.quadraticCurveTo(-8,16,-10,18);
    path.lineTo(-8,20);
    path.quadraticCurveTo(-6,18,-6,16);
    path.lineTo(-1,19);
    path.quadraticCurveTo(2.5,18,2,14);
    path.lineTo(2,4);
    path.quadraticCurveTo(2.5,1,4,2);
    path.lineTo(2,0);
   
    
var points = path.getPoints();

var geometry = new THREE.BufferGeometry().setFromPoints( points );
var material = new THREE.LineBasicMaterial( { color: 0xffffff } );

var line = new THREE.Line( geometry, material );
line.translateX(10);

scene.add( line );
renderer.render( scene, camera );



//Letra: E   ALTURA=20 LARGURA = 20
var path = new THREE.Path();
    path.moveTo(0,0);
    path.quadraticCurveTo(2,1,4,4);
    path.bezierCurveTo(-5,2,-6,10,-1,17);
    path.lineTo(2,15);
    path.lineTo(-4,8);
    path.lineTo(-3.6,7);
    path.lineTo(4,16);
    path.lineTo(0,18);
    path.bezierCurveTo(-8,14,-8,4,0,0);
 
var points = path.getPoints();

var geometry = new THREE.BufferGeometry().setFromPoints( points );
var material = new THREE.LineBasicMaterial( { color: 0xffffff } );

var line = new THREE.Line( geometry, material );
line.translateX(20);

scene.add( line );
renderer.render( scene, camera );

















/*

    ANIMAÇÃO

*/
/*
//Função de Animação    
function animate()
{
    requestAnimationFrame(animate);

    letter_A.rotation.x += 0.01;
    letter_A.rotation.y += 0.01;

    renderer.render( scene, camera );
}

//Chamada da função de Animação
animate();
*/




























/*

            MAKE A PATH IN A 3D GEOMETRY

*/



/*
var path = new THREE.Shape();
path.moveTo(1,2);
path.lineTo(3,4);
path.lineTo(-3,7);
path.quadraticCurveTo(-20,-5,-3,-20);
path.quadraticCurveTo(2,-17,3,-15);

path.quadraticCurveTo(-5,-18,-10,-5);
path.quadraticCurveTo(-10,0,-4,5);
path.lineTo(1,2);
   

    var extrudeSettings = {
        steps: 2,
        depth: 4,
        bevelEnabled: true,
        bevelThickness: 1,
        bevelSize: 1,
        bevelOffset: 0,
        bevelSegments: 1
    };
  
    var geometry = new THREE.ExtrudeGeometry( path, extrudeSettings );
    var material = new THREE.MeshBasicMaterial( { color: 0x00ff00 } );
    var letter_A = new THREE.Mesh( geometry, material ) ;

    scene.add( letter_A );
    renderer.render( scene, camera );
*/
