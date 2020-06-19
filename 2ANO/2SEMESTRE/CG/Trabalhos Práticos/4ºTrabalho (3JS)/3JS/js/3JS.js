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

/*

    CENA

*/
var scene = new THREE.Scene();
scene.background = new THREE.Color( 0x031d49 );     //Mudar a cor do Background


/*

    FUNDO

*/

var fundo = new THREE.PlaneGeometry(10000,10000,100,100);
var fundo_material = new THREE.MeshLambertMaterial( { color: 0x503009 } );
var fundo_mesh = new THREE.Mesh(fundo, fundo_material);
fundo_mesh.rotation.x = -90 * Math.PI / 180;
fundo_mesh.position.y = -25;
scene.add(fundo_mesh);


/*

    LIGHT

*/
var light = new THREE.AmbientLight(0xffff00, 0.5, 600);
light.position.set(0,100,0);
light.lookAt(0,0,0);
scene.add( light );

/*

    MODELO 3D DA PALAVRA : Canelado

*/
//Definições de Extrude
var extrudeSettings = {
    steps: 1,
    depth: 0.5,
    bevelEnabled: true,
    bevelThickness: 1,
    bevelSize: 0,
    bevelOffset: 0,
    bevelSegments: 1
};


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

    
    path_C.translateX(-60); //Translate letter C -20 units on X axis
    path_C.translateY(-3);

    scene.add( path_C );
    

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
   
   
    path_A.translateX(-42); //Translate letter C -20 units on X axis
    path_A.translateY(-20);


    scene.add( path_A );
   
   
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
    
    
    path_N.translateX(-22); 
    path_N.translateY(-23);


    scene.add( path_N );


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


    path_E.translateX(-8); //Translate letter C -20 units on X axis
    path_E.translateY(-23);


    scene.add( path_E );
   

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


    path_L.translateX(4); //Translate letter C -20 units on X axis
    path_L.translateY(-23);


    scene.add(  path_L );
   

//Letra A
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
    
    
    path_A2.translateX(20); //Translate letter C -20 units on X axis
    path_A2.translateY(-20);


    scene.add( path_A2 );


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


    path_D.translateX(40); //Translate letter C -20 units on X axis
    path_D.translateY(-20);


    scene.add( path_D );
 

//Letra O
var path_O_1 = new THREE.Shape();
    path_O_1.moveTo(0,0);
    path_O_1.quadraticCurveTo(15,4,2,20);
    path_O_1.quadraticCurveTo(-15,18,0,0);
    
    
    var geometry = new THREE.ExtrudeGeometry( path_O_1, extrudeSettings );
    var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
    var path_O_1 = new THREE.Mesh( geometry, material ) ;
    
    
    path_O_1.translateX(60); //Translate letter C -20 units on X axis
    path_O_1.translateY(-23);
    
    scene.add( path_O_1 );
  

    var extrudeSettings2 = {
        steps: 1,
        depth: 0.51,
        bevelEnabled: true,
        bevelThickness: 1,
        bevelSize: 0,
        bevelOffset: 0,
        bevelSegments: 1
    };    


var path_O_2 = new THREE.Shape();
    path_O_2.moveTo(0,4);
    path_O_2.bezierCurveTo(10,8,4,14,-1,17);
    path_O_2.bezierCurveTo(-10,16,-2,6,0,4);


    var geometry = new THREE.ExtrudeGeometry( path_O_2, extrudeSettings2 );
    var material = new THREE.MeshLambertMaterial( { color: 0x503009} );
    var path_O_2 = new THREE.Mesh( geometry, material ) ;
    
    
    path_O_2.translateX(60); //Translate letter C -20 units on X axis
    path_O_2.translateY(-23);


    scene.add( path_O_2 );




/*

    ANIMAÇÃO

*/ 
function animate()
{
    requestAnimationFrame(animate);
    //path_C.rotation.x += 0.01;
    //path_C.rotation.y += 0.01;

/*
    path_A.rotation.x += 0.01;
    path_A.rotation.y += 0.01;


    path_N.rotation.x += 0.01;
    path_N.rotation.y += 0.01;


    path_E.rotation.x += 0.01;
    path_E.rotation.y += 0.01;


    path_L.rotation.x += 0.01;
    path_L.rotation.y += 0.01;


    path_A2.rotation.x += 0.01;
    path_A2.rotation.y += 0.01


    path_D.rotation.x += 0.01;
    path_D.rotation.y += 0.01;


    path_O_1.rotation.x += 0.01;
    path_O_1.rotation.y += 0.01;
    path_O_2.rotation.x += 0.01;
    path_O_2.rotation.y += 0.01;
*/
    renderer.render( scene, camera );
}
//Chamada da função de Animação
animate();