precision highp float;

varying vec4 v_pos_screen;
varying vec4 v_pos_world;
varying vec3 v_norm;
varying vec2 v_uv;

uniform sampler2D u_tex;

void main()
{
	vec3 color = texture2D(u_tex, v_uv).xyz;
	
	vec3 light = normalize(vec3(0.0, 0.0, -2.0));
	
	float l = max(1.0, abs(dot(light, v_norm)));
	float dist = 1.3 / exp(length(v_pos_world.xyz - light));
	dist = min(1.0, dist);
	
  gl_FragColor = vec4(color * l * dist, 1.0);
}
