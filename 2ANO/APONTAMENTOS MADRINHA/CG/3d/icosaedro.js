function obj(phi){
    var points = [
                  new THREE.Vector3(   0,  phi,   1  ),
                  new THREE.Vector3(   1,   0,   phi ),
                  new THREE.Vector3(  -1,   0,   phi ), 
                  new THREE.Vector3( -phi,  1,    0  ), 
                  new THREE.Vector3(   0,  phi,  -1  ), 
                  new THREE.Vector3(  phi,  1,    0  ), 
                  new THREE.Vector3(   0, -phi,   1  ), 
                  new THREE.Vector3( -phi, -1,    0  ), 
                  new THREE.Vector3(  -1,   0,  -phi ),
                  new THREE.Vector3(   1,   0,  -phi ),
                  new THREE.Vector3(   1,   0,  -phi ),
                  new THREE.Vector3(  phi, -1,    0  ),
                  new THREE.Vector3(   0, -phi,  -1  )
                 ];
                 
    var faces = [
                  //top
                  new THREE.Face3(0,1,2),
                  new THREE.Face3(0,2,3),
                  new THREE.Face3(0,3,4),
                  new THREE.Face3(0,4,5),
                  new THREE.Face3(0,5,1),
                  
                  //top-side
                  new THREE.Face3(1,2,6),
                  new THREE.Face3(2,3,7),
                  new THREE.Face3(3,4,8),
                  new THREE.Face3(4,5,9),
                  new THREE.Face3(5,1,11),
                  
                  //bot-side
                  new THREE.Face3(6,7,2),
                  new THREE.Face3(7,8,3),
                  new THREE.Face3(8,9,4),
                  new THREE.Face3(9,11,5),
                  new THREE.Face3(11,6,1),
                  
                  //bot
                  new THREE.Face3(12,6,7),
                  new THREE.Face3(12,7,8),
                  new THREE.Face3(12,8,9),
                  new THREE.Face3(12,9,11),
                  new THREE.Face3(12,11,6),
                ];
                
  var geo = new THREE.Geometry();
  geo.vertices = points;
  geo.faces = faces;
  geo.computeFaceNormals(); // get the right "outside"
  geo.computeBoundingSphere(); // to help the rendering system
  
  var mesh =  new THREE.MeshLambertMaterial( {color: "blue" } );
  mesh.side = THREE.DoubleSide;
  var obj = new THREE.Mesh(geo, mesh);
  obj.position.set(0,0,0);
  
  return obj;
}   

/**
 * 
 * Setup the rendering context and build a model
 *
 **/         
function init(mesh) {  
  //
  //  Document
  //
  renderer = new THREE.WebGLRenderer({ alpha: true });
  renderer.setClearColor("white")
  renderer.setSize(512,512);
  document.body.appendChild(renderer.domElement);
  //
  //  Scene (World, Model)
  //
  scene = new THREE.Scene();
  //
  //  Camera (and TrackballControls)
  //
  camera = new THREE.PerspectiveCamera(
    35,       // abertura
    512/512,  // proporção largura/altura
    0.1,      // corte perto
    10000     // corte longe
    );
  camera.position.set( -2.5, 0, 20 );
  camera.lookAt( scene.position );
  controls = new THREE.TrackballControls(camera);
  //
  //  Lights
  //  
  var ambient_light = new THREE.AmbientLight(0x7F7F7F);
  scene.add( ambient_light );
  //
  var point_light_1 = new THREE.PointLight(0x3F3F3F);
  point_light_1.position.set(5, 0, 0);
  scene.add( point_light_1 );
  // //
  var point_light_2 = new THREE.PointLight(0x3F3F3F);
  point_light_2.position.set(-5, 0, 0);
  scene.add( point_light_2 );
  //
  //
  //
  scene.add( mesh );
  //
  //  Return camera, scene, etc
  //
  return {
    camera: camera,
    scene: scene,
    renderer: renderer,
    controls: controls
  }
}
/**
 *
 * Animate the model
 *
 */
function animate(step) {
  requestAnimationFrame( function() { animate(step); });
  step.controls.update();
  step.renderer.render( step.scene, step.camera);
}
/**
 *
 *  Entry function
 *
 */
function main() {   
    //const
    const phi = (Math.sqrt(5)+1)/2;

    var step = init(obj(phi));
    animate(step);
}

