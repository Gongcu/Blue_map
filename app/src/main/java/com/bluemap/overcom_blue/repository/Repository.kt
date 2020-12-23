package com.bluemap.overcom_blue.repository

import android.app.Application
import com.bluemap.overcom_blue.application.BaseApplication
import com.bluemap.overcom_blue.model.Center
import com.bluemap.overcom_blue.model.Comment
import com.bluemap.overcom_blue.model.Post
import com.bluemap.overcom_blue.model.User
import com.bluemap.overcom_blue.network.BluemapAPI
import com.bluemap.overcom_blue.network.Retrofit
import io.reactivex.Single
import retrofit2.Call

class Repository(val application: Application) {
    private val retrofit : retrofit2.Retrofit = Retrofit.getInstance()
    private val bluemapAPI = retrofit.create(BluemapAPI::class.java)
    private val userId = (application as BaseApplication).userId

    fun postUser(user: User):Single<User>{
        return bluemapAPI.postUser(user)
    }

    fun getPostById(postId:Int):Single<Post>{
        return bluemapAPI.getPostById(postId,userId)
    }

    fun getComment(postId: Int):Single<List<Comment>>{
        return bluemapAPI.getComment(postId,userId)
    }

    fun writeComment(postId: Int,comment:Comment):Single<List<Comment>>{
        return bluemapAPI.writeComment(postId, comment)
    }

    fun writeReplyComment(postId:Int, commentId: Int,comment: Comment): Single<List<Comment>> {
        return bluemapAPI.writeReplyComment(postId,commentId,comment)
    }

    fun likeComment(commentId: Int):Single<Boolean>{
        return bluemapAPI.likeComment(commentId,userId)
    }


    fun writePost(post: Post): Single<Void> {
        return bluemapAPI.writePost(post)
    }

    fun getPostList():Single<List<Post>> {
        return bluemapAPI.getPostList((application as BaseApplication).userId)
    }

    fun getCenter(lat:Double, lng:Double):Single<List<Center>>{
        return bluemapAPI.getCenter(lat,lng)
    }

    fun likePost(postId: Int):Single<Boolean>{
        return bluemapAPI.likePost(postId,userId)
    }
}