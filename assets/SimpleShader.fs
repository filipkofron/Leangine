precision highp float;

varying vec4 v_pos_screen;
varying vec4 v_pos_world;
varying vec3 v_norm;
varying vec4 v_camera_world;

void main()
{
	vec3 color = vec3(1.0, 0.8, 0.5);
	
	vec3 light = normalize(vec3(0.0, 0.0, -2.0));
	
	float l = max(1.0, abs(dot(light, v_norm)));
	float dist = 1.3 / exp(length(v_pos_world.xyz - light));
	dist = min(1.0, dist);
	
  gl_FragColor = vec4(color * l * dist, 1.0);
}
