
/*

    RENDERER

*/
/*
var renderer = new THREE.WebGLRenderer();
renderer.setSize( window.innerWidth, window.innerHeight );
document.body.appendChild( renderer.domElement );
*/

/*

    CAMERA

*/
/*
var camera = new THREE.PerspectiveCamera( 45, window.innerWidth / window.innerHeight, 1, 500 );
camera.position.set( 0, 0, 250 );
camera.lookAt( 0, 0, 0 );
*/

/*

    CENA

*/
/*
var scene = new THREE.Scene();
scene.background = new THREE.Color( 0x031d49 );     //Mudar a cor do Background
*/

/*

    FUNDO

*/
/*
var fundo = new THREE.PlaneGeometry(10000,10000,100,100);
var fundo_material = new THREE.MeshLambertMaterial( { color: 0x503009 } );
var fundo_mesh = new THREE.Mesh(fundo, fundo_material);
fundo_mesh.rotation.x = -90 * Math.PI / 180;
fundo_mesh.position.y = -25;
fundo_mesh.recieveShadow = true;
scene.add(fundo_mesh);
*/

/*

    LIGHT

*/
/*
//var light = new THREE.AmbientLight(0xffff00, 1, 600);
var light = new THREE.PointLight(0xffff00, 2, 600);
light.position.set(-50,0,100);
scene.add( light );
*/

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
function C(x,y)
{
    this.x = x; //1
    this.y = y; 

    
        var path_C = new THREE.Shape();
        path_C.moveTo(this.x,2);
        path_C.lineTo(this.x+2,3.5);
        path_C.lineTo(this.x-4,7);
        path_C.quadraticCurveTo(this.x-21,-5,this.x-3,-20);
        path_C.quadraticCurveTo(this.x+2,-17,this.x+2,-15);

        path_C.quadraticCurveTo(this.x-6,-18,this.x-11,-5);
        path_C.quadraticCurveTo(this.x-11,0,this.x-5,5);
        path_C.lineTo(this.x,2);



        var geometry = new THREE.ExtrudeGeometry( path_C, extrudeSettings );
        var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
        var path_C = new THREE.Mesh( geometry, material) ;

      
        var targetPosition1 = new THREE.Vector3( 70, 0, 0 );
        var tween1 = new TWEEN.Tween( path_C.position ).to( targetPosition1, 1000 );
        tween1.start(); 
        //path_C.translateX(-60); //Translate letter C -20 units on X axis
        //path_C.translateY(17);
        
        scene.add(path_C);
}  
  
/*
//Letra A
function A(x,y)
{
    this.x = x; //0
    this.y = y; //0

    var path_A = new THREE.Shape();
        path_A.moveTo(this.x,0);
        path_A.quadraticCurveTo(this.x+0.5,-2,this.x+3,-3);
        path_A.lineTo(this.x+5,-1);
        path_A.quadraticCurveTo(this.x+3,-1,this.x+3,0);
        path_A.lineTo(this.x+3,13);
        path_A.lineTo(this.x+4,14);
        path_A.lineTo(this.x-2,17);  //(-2,-3)
        path_A.quadraticCurveTo(this.x-8,15,this.x-9,14);
        path_A.lineTo(this.x-8,13);
        path_A.lineTo(this.x-5,15);
        path_A.lineTo(this.x,14);
        path_A.lineTo(this.x,0);

        path_A.moveTo(this.x,10);
        path_A.quadraticCurveTo(this.x-17,5,this.x-6,-3);
        path_A.quadraticCurveTo(this.x-2,-2.5,this.x,0);
        path_A.lineTo(this.x,2);
        path_A.bezierCurveTo(this.x-10,-4,this.x-10,8,this.x,8);


        var geometry = new THREE.ExtrudeGeometry( path_A, extrudeSettings );
        var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
        var path_A = new THREE.Mesh( geometry, material ) ;
    
    
        //path_A.translateX(-42); //Translate letter C -20 units on X axis
        path_A.translateY(0);


        scene.add( path_A );
}        
   
//Letra N
function N(x,y)
{
    this.x = x; //2
    this.y = y;

    var path_N = new THREE.Shape();
        path_N.moveTo(this.x,0);
        path_N.quadraticCurveTo(this.x-1.5, 1, this.x-2, 4);
        path_N.lineTo(this.x-2,14);
        path_N.quadraticCurveTo(this.x-5,16,this.x-8,14);
        path_N.lineTo(this.x-8,4);
        path_N.quadraticCurveTo(this.x-7,1,this.x-6,2);
        path_N.lineTo(this.x-8,0);
        path_N.quadraticCurveTo(this.x-10,2,this.x-10,4);
        path_N.lineTo(this.x-10,14);
        path_N.quadraticCurveTo(this.x-10,16,this.x-12,18);
        path_N.lineTo(this.x-10,20);
        path_N.quadraticCurveTo(this.x-8,18,this.x-8,16);
        path_N.lineTo(this.x-3,19);
        path_N.quadraticCurveTo(this.x+0.5,18,this.x,14);
        path_N.lineTo(this.x,4);
        path_N.quadraticCurveTo(this.x+0.5,1,this.x+2,2);
        path_N.lineTo(this.x,0);

            
        var geometry = new THREE.ExtrudeGeometry(path_N, extrudeSettings );
        var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
        var path_N = new THREE.Mesh( geometry, material ) ;
        
        
        //path_N.translateX(-22); 
        path_N.translateY(-3);


        scene.add( path_N );
}        

//Letra E
function E(x,y)
{
    this.x = x; //0
    this.y = y;
    var path_E = new THREE.Shape();
        path_E.moveTo(this.x = x,0);
        path_E.quadraticCurveTo(this.x = x+2,1,this.x = x+4,4);
        path_E.bezierCurveTo(this.x = x-5,2,this.x = x-6,10,this.x = x-1,18);
        path_E.lineTo(this.x = x+2,15);
        path_E.lineTo(this.x = x-4,8);
        path_E.lineTo(this.x = x-3.6,7);
        path_E.lineTo(this.x = x+4,16);
        path_E.lineTo(this.x = x,20);
        path_E.bezierCurveTo(this.x = x-8,14,this.x = x-8,4,this.x = x,0);
    

        var geometry = new THREE.ExtrudeGeometry( path_E, extrudeSettings );
        var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );;
        var path_E = new THREE.Mesh( geometry, material ) ;


        //path_E.translateX(-8); //Translate letter C -20 units on X axis
        path_E.translateY(-3);


        scene.add( path_E );
}   

//Letra L
function L(x,y)
{
    this.x = x; //0
    this.y = y;

    var path_L = new THREE.Shape();
        path_L.moveTo(this.x,0);
        path_L.lineTo(this.x+2,2);
        path_L.quadraticCurveTo(this.x-1,3,this.x-1,4);
        path_L.lineTo(this.x-1,27);
        path_L.lineTo(this.x-4,24);
        path_L.quadraticCurveTo(this.x-3,23,this.x-3,20);
        path_L.lineTo(this.x-3,4);
        path_L.quadraticCurveTo(this.x-3,1,this.x,0);


        var geometry = new THREE.ExtrudeGeometry(  path_L, extrudeSettings );
        var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
        var  path_L = new THREE.Mesh( geometry, material ) ;


        //path_L.translateX(4); //Translate letter C -20 units on X axis
        path_L.translateY(-3);


        scene.add(  path_L );
}   

//Letra D
function D(x,y)
{
    this.x = x; //0
    this.y = y;

    var path_D = new THREE.Shape();
        path_D.moveTo(this.x,0);
        path_D.quadraticCurveTo(this.x+0.5,-2,this.x+3,-3);
        path_D.lineTo(this.x+5,-1);
        path_D.quadraticCurveTo(this.x+3,-1,this.x+3,0);
        path_D.lineTo(this.x+3,24);
        path_D.lineTo(this.x-2,22);
        path_D.quadraticCurveTo(this.x,21.5,this.x,20);
            
        path_D.lineTo(this.x,14);
        path_D.lineTo(this.x-5,16);
        path_D.bezierCurveTo(this.x-14,10,this.x-14,1,this.x-3,-3);
        path_D.quadraticCurveTo(this.x-1,-2,this.x,0);
        path_D.lineTo(this.x,2);
        path_D.bezierCurveTo(this.x-8,-5,this.x-13,10,this.x-4,14);
        path_D.lineTo(this.x,12);
        path_D.lineTo(this.x,2);


        var geometry = new THREE.ExtrudeGeometry( path_D, extrudeSettings );
        var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
        var path_D = new THREE.Mesh( geometry, material ) ;


        //path_D.translateX(40); //Translate letter C -20 units on X axis
        path_D.translateY(0);


        scene.add( path_D );
} 

//Letra O
//->Parte Maior
function O_big(x,y)
{

    this.x = x;
    this.y = y;

var path_O_1 = new THREE.Shape();
    path_O_1.moveTo(this.x,0);
    path_O_1.quadraticCurveTo(this.x+15,4,this.x+2,20);
    path_O_1.quadraticCurveTo(this.x-15,18,this.x,0);
    
    
    var geometry = new THREE.ExtrudeGeometry( path_O_1, extrudeSettings );
    var material = new THREE.MeshLambertMaterial( { color: 0xF3FFE2} );
    var path_O_1 = new THREE.Mesh( geometry, material ) ;
    
    
    //path_O_1.translateX(55); //Translate letter C -20 units on X axis
    path_O_1.translateY(-3);
    
    scene.add( path_O_1 );
}  
//->Parte Menor
function O_small(x,y)
{
    this.x = x; //0
    this.y = y;

    var path_O_2 = new THREE.Shape();
        path_O_2.moveTo(this.x,4);
        path_O_2.bezierCurveTo(this.x+10,8,this.x+4,14,this.x-1,17);
        path_O_2.bezierCurveTo(this.x-10,16,this.x-2,6,this.x,4);


        var extrudeSettings2 = {
            steps: 1,
            depth: 0.51,
            bevelEnabled: true,
            bevelThickness: 1,
            bevelSize: 0,
            bevelOffset: 0,
            bevelSegments: 1
        };    


        var geometry = new THREE.ExtrudeGeometry( path_O_2, extrudeSettings2 );
        var material = new THREE.MeshLambertMaterial( { color: 0x031d49} );
        var path_O_2 = new THREE.Mesh( geometry, material ) ;
        
        
        //path_O_2.translateX(55); //Translate letter C -20 units on X axis
        path_O_2.translateY(-3);


        scene.add( path_O_2 );
}
*/

/*

    MAIN

*/ 
function main()
{
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
    fundo_mesh.recieveShadow = true;
    scene.add(fundo_mesh);


    /*
        LIGHT
    */
    var light = new THREE.PointLight(0xffff00, 2, 600);
    light.position.set(-50,0,100);
    scene.add( light );



    //Construção da Palavra: "Canelado"
    letterC = new C(-50,0); 
    

    /*letterA = new A(-30,0);  
    letterN = new N(-10,0);
    letterE = new E(10,0);
    letterL = new L(30,0);
    letterA2 = new A(50,0);
    letterD = new D(70,0);
    letterO_Big = new O_big(90,0);
    letterO_Small = new O_small(90,0);*/

    function animate(time)
    {
        requestAnimationFrame(animate);
        
        TWEEN.update(time);
        

        renderer.render( scene, camera );
    }
    animate();



    //Função de Animação
    //function animate()
    //{
        //requestAnimationFrame(animate);
        
        //letterC.update();

        /*
        letterC.rotation.x += 0.01;
        letterC.rotation.y += 0.01;


        letterA.rotation.x += 0.01;
        letterA.rotation.y += 0.01;


        letterN.rotation.x += 0.01;
        letterN.rotation.y += 0.01;


        letterE.rotation.x += 0.01;
        letterE.rotation.y += 0.01;


        letterL.rotation.x += 0.01;
        letterL.rotation.y += 0.01;


        letterA2.rotation.x += 0.01;
        letterA2.rotation.y += 0.01


        letterD.rotation.x += 0.01;
        letterD.rotation.y += 0.01;


        letterO_Big.rotation.x += 0.01;
        letterO_Big.rotation.y += 0.01;
        letterO_Small.rotation.x += 0.01;
        letterO_Small.rotation.y += 0.01;*/
        //renderer.render( scene, camera );
    //}
    //Chamada da função de Animação
    //animate();

    
}