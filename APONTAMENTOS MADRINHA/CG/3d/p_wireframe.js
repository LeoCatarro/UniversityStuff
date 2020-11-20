function obj(){
    var points = [
                  new THREE.Vector3( 1, 1, 0),
                  new THREE.Vector3( 1,-1, 0),
                  new THREE.Vector3(-1,-1, 0),
                  new THREE.Vector3(-1, 1, 0),
                  new THREE.Vector3( 0, 0, 3)
                 ];
                 
    var faces = [
                  //base
                  new THREE.Face3(0,1,2),
                  new THREE.Face3(2,3,0),
                  
                  //wall
                  new THREE.Face3(4,0,1),
                  new THREE.Face3(4,1,2),
                  new THREE.Face3(4,2,3),
                  new THREE.Face3(4,3,0),
                  
                ];
                
  var geo = new THREE.Geometry();
  geo.vertices = points;
  geo.faces = faces;
  geo.computeFaceNormals(); // get the right "outside"
  geo.computeBoundingSphere(); // to help the rendering system
  
  var mesh =  new THREE.MeshLambertMaterial( {color: "blue", wireframe:true} );
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

    var step = init(obj());
    animate(step);
}

