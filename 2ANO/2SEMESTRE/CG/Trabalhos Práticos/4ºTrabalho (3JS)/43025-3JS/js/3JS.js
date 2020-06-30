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
camera.position.set( 0, 0, 250 );
camera.lookAt( 0, 0, 0 );
var angle = 0;
var radius = 200; 


/*

    CENA

*/
var scene = new THREE.Scene();


/*

    FUNDO

*/
//Paredes
var fundo2 = new THREE.PlaneGeometry(10000,10000,100,100);
var fundo_material2 = new THREE.MeshLambertMaterial( { color: 0x0240c7 } );
var fundo_mesh2 = new THREE.Mesh(fundo2, fundo_material2);
fundo_mesh2.position.z = -200;
scene.add(fundo_mesh2);

var fundo3 = new THREE.PlaneGeometry(10000,10000,100,100);
var fundo_material3 = fundo_material2;
var fundo_mesh3 = new THREE.Mesh(fundo3, fundo_material3);
fundo_mesh3.rotation.x = Math.PI;
fundo_mesh3.position.y = 200;
fundo_mesh3.position.z = 200;
scene.add( fundo_mesh3 );


//Chão
var fundo = new THREE.PlaneGeometry(10000,10000,100,100);
var fundo_material = new THREE.MeshLambertMaterial( { color: 0x503009 } );
var fundo_mesh = new THREE.Mesh(fundo, fundo_material);
fundo_mesh.rotation.x = -90 * Math.PI / 180;
fundo_mesh.position.y = -30;
scene.add(fundo_mesh);


/*

    LIGHT

*/
var light = new THREE.PointLight(0xFFFF33, 1, 600);
light.position.set(-50,0,100);
scene.add( light );


//Definições de Extrude
var extrudeSettings = {
    steps: 1,
    depth: 1,
    bevelEnabled: true,
    bevelThickness: 1,
    bevelSize: 0,
    bevelOffset: 0,
    bevelSegments: 1
}
//Definições de Extrude(parte menor da letra: "O")
var extrudeSettings2 = {
    steps: 1,
    depth: 1.01,
    bevelEnabled: true,
    bevelThickness: 1,
    bevelSize: 0,
    bevelOffset: 0,
    bevelSegments: 1
};



/*

    MODELO 3D DA PALAVRA : Canelado

*/
function letterC()
{
//Letra C
    var path_C = new THREE.Shape();
    path_C.moveTo(1,2);
    path_C.lineTo(3,3.5);
    path_C.lineTo(-3,7);
    path_C.quadraticCurveTo(-20,-5,-3,-20);
    path_C.quadraticCurveTo(2,-17,3,-15);

    path_C.quadraticCurveTo(-5,-18,-10,-5);
    path_C.quadraticCurveTo(-10,0,-4,5);
    path_C.lineTo(1,2);


    var geometry = new THREE.ExtrudeGeometry( path_C, extrudeSettings );
    var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
    var path_C = new THREE.Mesh( geometry, material) ;

    path_C.position.set(-300, 17, 0);
    

    var targetPositionC = new THREE.Vector3( -70, 17, 0 );
    var tweenC = new TWEEN.Tween( path_C.position ).to( targetPositionC, 1000 ); 
    tweenC.start();

    scene.add( path_C );
} 
    

function letterA()
{
//Letra A
var path_A = new THREE.Shape();
    path_A.moveTo(0,0);
    path_A.quadraticCurveTo(0.5,-2,3,-3);
    path_A.lineTo(5,-1);
    path_A.quadraticCurveTo(3,-1,3,0);
    path_A.lineTo(3,13);
    path_A.lineTo(4,14);
    path_A.lineTo(-2,17);  //(-2,-3)
    path_A.quadraticCurveTo(-8,15,-9,14);
    path_A.lineTo(-8,13);
    path_A.lineTo(-5,15);
    path_A.lineTo(0,14);
    path_A.lineTo(0,0);

    path_A.moveTo(0,10);
    path_A.quadraticCurveTo(-17,5,-6,-3);
    path_A.quadraticCurveTo(-2,-2.5,0,0);
    path_A.lineTo(0,2);
    path_A.bezierCurveTo(-10,-4,-10,8,0,8);


    var geometry = new THREE.ExtrudeGeometry( path_A, extrudeSettings );
    var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
    var path_A = new THREE.Mesh( geometry, material ) ;
   
    path_A.position.set(-300, 0, 0);
    
    var targetPositionA = new THREE.Vector3( -50, 0, 0 );
    var tweenA = new TWEEN.Tween( path_A.position ).to( targetPositionA, 1000 ); 
    tweenA.start();
   

    scene.add( path_A );
}  
 

function letterN()
{
//Letra N
var path_N = new THREE.Shape();
    path_N.moveTo(2,0);
    path_N.quadraticCurveTo(0.5,1,0,4);
    path_N.lineTo(0,14);
    path_N.quadraticCurveTo(-3,16,-6,14);
    path_N.lineTo(-6,4);
    path_N.quadraticCurveTo(-5,1,-4,2);
    path_N.lineTo(-6,0);
    path_N.quadraticCurveTo(-8,2,-8,4);
    path_N.lineTo(-8,14);
    path_N.quadraticCurveTo(-8,16,-10,18);
    path_N.lineTo(-8,20);
    path_N.quadraticCurveTo(-6,18,-6,16);
    path_N.lineTo(-1,19);
    path_N.quadraticCurveTo(2.5,18,2,14);
    path_N.lineTo(2,4);
    path_N.quadraticCurveTo(2.5,1,4,2);
    path_N.lineTo(2,0);

        
    var geometry = new THREE.ExtrudeGeometry(path_N, extrudeSettings );
    var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
    var path_N = new THREE.Mesh( geometry, material ) ;
    
    path_N.position.set(-300, -3, 0);
    

    var targetPositionN = new THREE.Vector3( -30, -3, 0 );
    var tweenN = new TWEEN.Tween( path_N.position ).to( targetPositionN, 1000 ); 
    tweenN.start();
    
  
    scene.add( path_N );
}


function letterE()
{
//Letra E
var path_E = new THREE.Shape();
    path_E.moveTo(0,0);
    path_E.quadraticCurveTo(2,1,4,4);
    path_E.bezierCurveTo(-5,2,-6,10,-1,18);
    path_E.lineTo(2,15);
    path_E.lineTo(-4,8);
    path_E.lineTo(-3.6,7);
    path_E.lineTo(4,16);
    path_E.lineTo(0,20);
    path_E.bezierCurveTo(-8,14,-8,4,0,0);
 

    var geometry = new THREE.ExtrudeGeometry( path_E, extrudeSettings );
    var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );;
    var path_E = new THREE.Mesh( geometry, material ) ;


    path_E.position.set(-300, -3, 0);
    

    var targetPositionE = new THREE.Vector3( -15, -3, 0 );
    var tweenE = new TWEEN.Tween( path_E.position ).to( targetPositionE, 1000 ); 
    tweenE.start();


    scene.add( path_E );
}
 
  
function letterL()
{
//Letra L
var path_L = new THREE.Shape();
    path_L.moveTo(0,0);
    path_L.lineTo(2,2);
    path_L.quadraticCurveTo(-1,3,-1,4);
    path_L.lineTo(-1,27);
    path_L.lineTo(-4,24);
    path_L.quadraticCurveTo(-3,23,-3,20);
    path_L.lineTo(-3,4);
    path_L.quadraticCurveTo(-3,1,0,0);


    var geometry = new THREE.ExtrudeGeometry(  path_L, extrudeSettings );
    var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
    var  path_L = new THREE.Mesh( geometry, material ) ;


    path_L.position.set(-300, -3, 0);
    

    var targetPositionL = new THREE.Vector3( 0, -3, 0 );
    var tweenL = new TWEEN.Tween( path_L.position ).to( targetPositionL, 1000 ); 
    tweenL.start();


    scene.add(  path_L );
}


function letterA2()
{
//Letra A2
var path_A2 = new THREE.Shape();
    path_A2.moveTo(0,0);
    path_A2.quadraticCurveTo(0.5,-2,3,-3);
    path_A2.lineTo(5,-1);
    path_A2.quadraticCurveTo(3,-1,3,0);
    path_A2.lineTo(3,13);
    path_A2.lineTo(4,14);
    path_A2.lineTo(-2,17);  //(-2,-3)
    path_A2.quadraticCurveTo(-8,15,-9,14);
    path_A2.lineTo(-8,13);
    path_A2.lineTo(-5,15);
    path_A2.lineTo(0,14);
    path_A2.lineTo(0,0);

    path_A2.moveTo(0,10);
    path_A2.quadraticCurveTo(-17,5,-6,-3);
    path_A2.quadraticCurveTo(-2,-2.5,0,0);
    path_A2.lineTo(0,2);
    path_A2.bezierCurveTo(-10,-4,-10,8,0,8);


    var geometry = new THREE.ExtrudeGeometry( path_A2, extrudeSettings );
    var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
    var path_A2 = new THREE.Mesh( geometry, material ) ;
    
    
    path_A2.position.set(-300, 0, 0);
    

    var targetPositionA2 = new THREE.Vector3( 18, 0, 0 );
    var tweenA2 = new TWEEN.Tween( path_A2.position ).to( targetPositionA2, 1000 ); 
    tweenA2.start();


    scene.add( path_A2 );
}


function letterD()
{
//Letra D
var path_D = new THREE.Shape();
    path_D.moveTo(0,0);
    path_D.quadraticCurveTo(0.5,-2,3,-3);
    path_D.lineTo(5,-1);
    path_D.quadraticCurveTo(3,-1,3,0);
    path_D.lineTo(3,24);
    path_D.lineTo(-2,22);
    path_D.quadraticCurveTo(0,21.5,0,20);
        
    path_D.lineTo(0,14);
    path_D.lineTo(-5,16);
    path_D.bezierCurveTo(-14,10,-14,1,-3,-3);
    path_D.quadraticCurveTo(-1,-2,0,0);
    path_D.lineTo(0,2);
    path_D.bezierCurveTo(-8,-5,-13,10,-4,14);
    path_D.lineTo(0,12);
    path_D.lineTo(0,2);


    var geometry = new THREE.ExtrudeGeometry( path_D, extrudeSettings );
    var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
    var path_D = new THREE.Mesh( geometry, material ) ;

    path_D.position.set(-300, 0, 0);

    var targetPositionD = new THREE.Vector3( 40, 0, 0 );
    var tweenD = new TWEEN.Tween( path_D.position ).to( targetPositionD, 1000 ); 
    tweenD.start();

    scene.add( path_D );
}


function letterO()
{
//Letra O
var path_O_2 = new THREE.Shape();
    path_O_2.moveTo(0,4);
    path_O_2.bezierCurveTo(10,8,4,14,-1,17);
    path_O_2.bezierCurveTo(-10,16,-2,6,0,4);


var path_O_1 = new THREE.Shape();
    path_O_1.moveTo(0,0);
    path_O_1.quadraticCurveTo(15,4,2,20);
    path_O_1.quadraticCurveTo(-15,18,0,0);
    

    path_O_1.holes.push(path_O_2);
    

    var geometry = new THREE.ExtrudeGeometry( path_O_1, extrudeSettings );
    var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
    var path_O_1 = new THREE.Mesh( geometry, material ) ;
    

    path_O_1.position.set(-300, -3, 0);

    
    var targetPositionO = new THREE.Vector3( 58, -3, 0 );
    var tweenO = new TWEEN.Tween( path_O_1.position ).to( targetPositionO, 1000 ); 
    tweenO.start();
    
    

    scene.add( path_O_1 );
} 
    

/*

    ANIMAÇÃO

*/
function animate(time)
{
    requestAnimationFrame(animate);

    if(TWEEN.update(time) == true)
    {
        TWEEN.update( time );
    }

    else 
    {
        camera.position.x = radius * Math.cos( angle );
        camera.position.z = radius * Math.sin( angle );
        angle += 0.01;
        camera.lookAt(0 ,0 ,0);
    } 

    renderer.render( scene, camera );
}
   


/*

    MAIN

*/
function main()
{ 
    //Construção da Palavra: Canelado
    letterC();
    letterA();
    letterN();
    letterE();
    letterL();
    letterA2();
    letterD();
    letterO();

    animate();
}