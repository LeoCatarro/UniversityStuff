//
// Classe genérica para saturno
//
Saturn = function() {
	Sim.Object.call(this);
}
//
// Subclasse de Sim.Object
//
Saturn.prototype = new Sim.Object();
//
// Inicialização de saturno
//
Saturn.prototype.init = function(param) {
	param = param || {};
    //
    //  Fazer um grupo com a órbita, saturno e os anéis
    //
    var planetOrbitGroup = new THREE.Object3D();
    //
    //  Informar a simulação deste objecto
    //
    this.setObject3D(planetOrbitGroup);
    //
    //  Fazer um grupo com saturno
    //
    var planetGroup = new THREE.Object3D();
    var distance = param.distance || 0;
    var distsquared = distance * distance;
    planetGroup.position.set(Math.sqrt(distsquared/2), 0, -Math.sqrt(distsquared/2));
    planetOrbitGroup.add(planetGroup);
    //
    this.planetGroup = planetGroup;
    var size = param.size || 1;
    this.planetGroup.scale.set(size, size, size);
    this.planetGroup.rotation.x = Saturn.TILT;
    //
    //  Fazer o planeta
    //
	this.createGlobe();
    //
    //  Fazer os anéis
    //
	this.createRings();
    //
    this.animateOrbit = param.animateOrbit;
    this.animateRotation = param.animateRotation;
    this.showOrbit = param.showOrbit;
    this.period = param.period;
    this.daylen = param.daylen;
    this.revolutionSpeed = param.revolutionSpeed ? param.revolutionSpeed : Saturn.REVOLUTION_Y;
    this.rotationSpeed = this.daylen;	
}

Saturn.prototype.createGlobe = function(map) {
    //
    //  Fazer saturno com um mapa de cores
    //
    var geometry = new THREE.SphereGeometry(1, 32, 32);
    //
    var saturnmap = "../media/saturn.jpg";
    var texture = THREE.ImageUtils.loadTexture(saturnmap);
    var material = new THREE.MeshPhongMaterial({
    	map: texture,
    	ambient: 0x333333 });
    //
    var mesh = new THREE.Mesh( geometry, material ); 
    //
    // Juntar a mesh ao grupo do planeta
    //
    this.planetGroup.add(mesh);
    //
    // Guardar uma referência de forma a podermos aceder-lhe directamente (por exemplo, para rodar)
    //
    this.globeMesh = mesh;
}

Saturn.prototype.createRings = function() {
    //
    //  Fazer os anéis de saturno com um mapa de cores
    //
    var geometry = new Saturn.Rings(1.1, 1.867, 64);
    //
    var ringsmap = "../media/saturn_ring.png";    
    var texture = THREE.ImageUtils.loadTexture(ringsmap);
    var material = new THREE.MeshLambertMaterial( {
    	map: texture,
    	transparent:true,
    	ambient:0xffffff } );
    material.side = THREE.DoubleSide;
   	//
    var mesh = new THREE.Mesh( geometry, material );
    //
    mesh.rotation.x = Math.PI / 2;
    //
    // Juntar a mesh ao grupo do planeta
    //
    this.planetGroup.add(mesh);
    //
    // Guardar uma referência de forma a podermos aceder-lhe directamente (por exemplo, para rodar)
    //
    this.ringsMesh = mesh;
}

Saturn.prototype.update = function() {	
    //
    //  Simular a órbita (em torno do sol): anos
    //
	if (this.animateOrbit)	{
		this.object3D.rotation.y += this.revolutionSpeed / this.period;
	}
    //
    //  Simular a rotação (em torno do eixo): dias
    //
    if (this.animateRotation)  {
        this.planetGroup.rotation.y += this.daylen;
    } 
    //
    //  Chamar o método da superclasse
    //
	Sim.Object.prototype.update.call(this);
}

Saturn.TILT = -0.466;
Saturn.REVOLUTION_Y = 0.003;
//
// Os anéis de saturno são definidos por uma geometria "à medida".
//
Saturn.Rings = function ( innerRadius, outerRadius, nSegments ) {
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
//
//  Subclasse de THREE.Geometry
//
Saturn.Rings.prototype = Object.create( THREE.Geometry.prototype ) ;
//
//  Construtor
//
Saturn.Rings.prototype.constructor = Saturn.Rings;

