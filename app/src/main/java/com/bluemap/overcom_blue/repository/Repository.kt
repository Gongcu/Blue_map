package com.bluemap.overcom_blue.repository

import android.app.Application
import com.bluemap.overcom_blue.application.BaseApplication
import com.bluemap.overcom_blue.model.Center
import com.bluemap.overcom_blue.model.Comment
import com.bluemap.overcom_blue.model.Post
import com.bluemap.overcom_blue.model.User
import com.bluemap.overcom_blue.network.BluemapAPI
import com.bluemap.overcom_blue.network.Retrofit
import com.bluemap.overcom_blue.util.Util
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response

class Repository(val application: Application) {
    private val retrofit : retrofit2.Retrofit = Retrofit.getInstance()
    private val bluemapAPI = retrofit.create(BluemapAPI::class.java)
    private val userId = (application as BaseApplication).userId

    fun postUser(user: User):Single<User>{
        return bluemapAPI.postUser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())

    }

    fun patchNickname(user: User):Single<User>{
        return bluemapAPI.patchNickname(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getPostById(postId:Int):Single<Post>{
        return bluemapAPI.getPostById(postId,userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun getComment(postId: Int):Single<List<Comment>>{
        return bluemapAPI.getComment(postId,userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun writeComment(postId: Int,comment:Comment):Single<List<Comment>>{
        return bluemapAPI.writeComment(postId, comment)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun writeReplyComment(postId:Int, commentId: Int,comment: Comment): Single<List<Comment>> {
        return bluemapAPI.writeReplyComment(postId,commentId,comment)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun likeComment(commentId: Int):Single<Boolean>{
        return bluemapAPI.likeComment(commentId,userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }


    fun writePost(post: Post): Completable {
        return bluemapAPI.writePost(post)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun getPostList(offset:Int):Single<List<Post>> {
        return bluemapAPI.getPostList((application as BaseApplication).userId,offset)
                //.observeOn(AndroidSchedulers.mainThread())
                //.subscribeOn(Schedulers.io())
    }

    fun getCenter(lat:Double, lng:Double):Single<List<Center>>{
        return bluemapAPI.getCenter(lat,lng)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun likePost(postId: Int):Single<Boolean>{
        return bluemapAPI.likePost(postId,userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}