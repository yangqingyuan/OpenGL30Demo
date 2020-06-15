package com.yibasan.opengl30demo.util

import android.opengl.GLES20
import android.opengl.GLES30
import android.util.Log

object ShardUtils {

    /**
     * 加载着色器
     */
    fun loadShader(shaderType: Int, source: String?): Int {
        /**
         * 创建和设置,编译顶点作色器
         */
        var shardIndex = GLES30.glCreateShader(shaderType)
        GLES30.glShaderSource(shardIndex, source)
        GLES30.glCompileShader(shardIndex)
        var compiler = IntArray(1)
        GLES30.glGetShaderiv(shardIndex, GLES30.GL_COMPILE_STATUS, compiler, 0)
        if (compiler[0] ==0 ) {
            Log.e("TAG", "Could not compile shader:${shaderType},source=$source")
            Log.e("TAG", "GLES20 Error:" + GLES20.glGetShaderInfoLog(GLES30.GL_VERTEX_SHADER))
            GLES30.glDeleteShader(shardIndex)
            shardIndex = 0
        }
        return shardIndex
    }

    /**
     * 创建程序
     */
    fun createProgram(vertexShardIndex:Int,fragmentShardIndex:Int): Int {
        var programIndex = GLES30.glCreateProgram()
        if(programIndex == 0){
            Log.e("TAG","create Program Error")
        }
        GLES30.glAttachShader(programIndex,vertexShardIndex)
        GLES30.glAttachShader(programIndex,fragmentShardIndex)

        return 0
    }

    /**
     * 链接程序
     */
    fun linkProgram(programIndex:Int):Int {
        GLES30.glLinkProgram(programIndex)
        var compiler = IntArray(1)
        GLES30.glGetProgramiv(programIndex,GLES30.GL_LINK_STATUS,compiler,0)
        if(compiler[0] != GLES30.GL_TRUE){
            Log.e("TAG","glLinkProgram Program Error")
            GLES30.glDeleteProgram(programIndex)
            return 0
        }
        return programIndex
    }
}