precision highp float;

varying vec4 v_pos_screen;
varying vec4 v_pos_world;
varying vec3 v_norm;

void main()
{
	float l = length(v_pos_screen);
	l += length(v_pos_world);
	l += length(v_norm);
	l *= 0.1;
  gl_FragColor = vec4(l + 0.5, 0.0, l, 1.0);
}
