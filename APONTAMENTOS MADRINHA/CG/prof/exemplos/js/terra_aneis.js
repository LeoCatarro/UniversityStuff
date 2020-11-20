// Constructor
EarthApp = function()
{
	Sim.App.call(this);
}

// Subclass Sim.App
EarthApp.prototype = new Sim.App();

// Our custom initializer
EarthApp.prototype.init = function(param)
{
	// Call superclass init code to set up scene, renderer, default camera
	Sim.App.prototype.init.call(this, param);
	
    // Create the Earth and add it to our sim
    var earth = new Earth();
    earth.init();
    this.addObject(earth);
}

// Custom Earth class
Earth = function()
{
	Sim.Object.call(this);
}

Earth.prototype = new Sim.Object();

Earth.prototype.init = function()
{
    
    // Tell the framework about our object
    var group = new THREE.Object3D();
    this.setObject3D(group);
    this.earthGroup = group;
    //
    //
    //
    this.createGlobe();
    this.createRings();
}

Earth.prototype.createGlobe = function() {   
    //
    //  Create globe
    //
    var geometry = new THREE.SphereGeometry(1, 32, 32);
    //
    //
    //
    var earthmap = "../media/earth_surface.jpg";
    var texture = THREE.ImageUtils.loadTexture(earthmap);
    var material = new THREE.MeshBasicMaterial( { map: texture} );
    material.side = THREE.DoubleSide;
    //
    //
    //
    var mesh = new THREE.Mesh( geometry, material ); 
    mesh.rotation.x = Earth.TILT;
    //
    //
    //
    this.earthGroup.add(mesh)
}
//
//
//
Earth.prototype.createRings = function() {      
    //
    //  Create rings
    //
    var geometry = new Earth.Rings(1.1, 1.5, 64);
    //
    //
    //
    var ringsmap = "../media/saturn_ring.png";     
    var texture = THREE.ImageUtils.loadTexture(ringsmap);
    var material = new THREE.MeshLambertMaterial( {
        map: texture,
        transparent:true,
        ambient:0xffffff } );
    material.side = THREE.DoubleSide;
    //
    //
    //
    var mesh = new THREE.Mesh( geometry, material );
    mesh.rotation.x = Math.PI/2 * 0.8;
    //
    //
    //
    this.earthGroup.add(mesh);
    //
    //
    //
    this.ringsMesh = mesh;
}

Earth.prototype.update = function()
{
	// "I feel the Earth move..."
	this.object3D.rotation.y += Earth.ROTATION_Y;
}

Earth.ROTATION_Y = 0.0025;
Earth.TILT = 0.41;

// The rings
Earth.Rings = function ( innerRadius, outerRadius, nSegments ) {   
    THREE.Geometry.call( this );

    var outerRadius = outerRadius || 1,
    innerRadius = innerRadius || .5,
    gridY = nSegments || 10;
    
    var i, twopi = 2 * Math.PI,
    uvs = [],
    iVer = Math.max( 2, gridY );

    for ( i = 0; i < ( iVer + 1 ) ; i++ ) {
        var fRad1 = i / iVer;
        var fRad2 = (i + 1) / iVer;
        //
        //
        //
        var fX1 = innerRadius * Math.cos( fRad1 * twopi );
        var fY1 = innerRadius * Math.sin( fRad1 * twopi );
        //
        var fX2 = outerRadius * Math.cos( fRad1 * twopi );
        var fY2 = outerRadius * Math.sin( fRad1 * twopi );
        //
        var fX3 = outerRadius * Math.cos( fRad2 * twopi );
        var fY3 = outerRadius * Math.sin( fRad2 * twopi );
        //
        var fX4 = innerRadius * Math.cos( fRad2 * twopi );
        var fY4 = innerRadius * Math.sin( fRad2 * twopi );
        //
        //
        //
        var v1 = new THREE.Vector3( fX1, fY1, 0 );
        //
        var v2 = new THREE.Vector3( fX2, fY2, 0 );
        //
        var v3 = new THREE.Vector3( fX3, fY3, 0 );
        //
        var v4 = new THREE.Vector3( fX4, fY4, 0 );
        //
        //
        //
        this.vertices.push( v1 );
        //
        this.vertices.push( v2 );
        //
        this.vertices.push( v3 );
        //
        this.vertices.push( v4 ); 
        //
        //
        //
        uvs.push( new THREE.Vector2(
            0.5 * v1.x/outerRadius,
            0.5 * v1.y/outerRadius ));
        //
        uvs.push( new THREE.Vector2(
            0.5 * v2.x/outerRadius,
            0.5 * v2.y/outerRadius ));
        //
        uvs.push( new THREE.Vector2(
            0.5 * v3.x/outerRadius,
            0.5 * v3.y/outerRadius ));
        //
        uvs.push( new THREE.Vector2(
            0.5 * v4.x/outerRadius,
            0.5 * v4.y/outerRadius ));   
    }
    
    var n = new THREE.Vector3( 0, 0, 1 );
    
    for ( i = 0; i < iVer ; i++ ) {
        var k = 4 * i;
        //
        this.faces.push(new THREE.Face3(
            k + 0,
            k + 1,
            k + 2,
            [ n.clone(), n.clone(), n.clone() ] ));
        this.faces.push(new THREE.Face3(
            k + 0,
            k + 2,
            k + 3,
            [ n.clone(), n.clone(), n.clone() ] ));
        //
        //
        //
        this.faceVertexUvs[ 0 ].push([
            new THREE.Vector2(0,0),
            new THREE.Vector2(1,0),
            new THREE.Vector2(1,1) ] );        
        this.faceVertexUvs[ 0 ].push([
            new THREE.Vector2(0,0),
            new THREE.Vector2(1,1),
            new THREE.Vector2(0,1) ] );
    }   

    this.computeFaceNormals();
    this.boundingSphere = new THREE.Sphere( new THREE.Vector3(), outerRadius );
};

Earth.Rings.prototype = Object.create( THREE.Geometry.prototype ) ;
Earth.Rings.prototype.constructor = Earth.Rings;

