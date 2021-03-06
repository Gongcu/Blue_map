package com.bluemap.overcom_blue.repository


import com.bluemap.overcom_blue.model.*
import com.bluemap.overcom_blue.network.BluemapAPI
import com.bluemap.overcom_blue.user.UserManager
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
        private val bluemapAPI: BluemapAPI
) {

    fun postUser(user: User):Single<User>{
        return bluemapAPI.postUser(user)
    }

    fun patchNickname(user: User):Single<User>{
        return bluemapAPI.patchNickname(user)
    }

    fun getPostById(postId:Int):Single<Post>{
        return bluemapAPI.getPostById(postId, UserManager.userId)
    }

    fun getComments(postId: Int):Single<List<Comment>>{
        return bluemapAPI.getComment(postId,UserManager.userId)
    }

    fun writeComment(postId: Int,comment:Comment):Single<List<Comment>>{
        return bluemapAPI.writeComment(postId, comment)
    }

    fun writeReplyComment(postId:Int, commentId: Int,comment: Comment): Single<List<Comment>> {
        return bluemapAPI.writeReplyComment(postId,commentId,comment)
    }

    fun likeComment(commentId: Int,):Single<Boolean>{
        return bluemapAPI.likeComment(commentId,UserManager.userId)
    }

    fun writePost(post: Post): Completable {
        return bluemapAPI.writePost(post)
    }

    fun getNotice():Single<Post> {
        return bluemapAPI.getNotice(UserManager.userId)
    }

    fun getPostList(offset:Int):Single<List<Post>> {
        return bluemapAPI.getPostList(UserManager.userId,offset)
    }

    fun getCenter(location: Location):Single<List<Center>>{
        return bluemapAPI.getCenter(location.latitude,location.longitude)
    }
    fun getCenter(search:String, offset:Int):Single<List<Center>>{
        return bluemapAPI.getCenterList(search,offset)
    }

    fun likePost(postId: Int):Single<Boolean>{
        return bluemapAPI.likePost(postId,UserManager.userId)
    }
}