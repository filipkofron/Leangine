precision highp float;


attribute vec3 a_pos;
attribute vec3 a_norm;
attribute vec4 a_color;

varying vec4 v_pos_screen;
varying vec4 v_pos_world;
varying vec3 v_norm;
varying vec4 v_color;

uniform mat4 u_model;
uniform mat4 u_view;
uniform mat4 u_proj;
uniform mat4 u_norm;

void main()
{
	v_pos_world = u_model * vec4(a_pos, 1.0);
	v_pos_screen = (u_proj * u_view) * v_pos_world;
	v_norm = normalize((u_norm * vec4(a_norm, 0.0)).xyz);
	v_color = a_color;
	
	gl_Position = v_pos_screen;
}
