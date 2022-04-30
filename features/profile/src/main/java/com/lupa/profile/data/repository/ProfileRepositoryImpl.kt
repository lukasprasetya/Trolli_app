package com.lupa.profile.data.repository

import com.lupa.profile.data.entity.User
import com.lupa.core.event.MutableStateEventManager
import com.lupa.core.event.StateEventManager
import com.lupa.core.local.DataPreferences
import io.reactivex.disposables.CompositeDisposable
import com.lupa.core.util.fetchStateEventSubscriber
import com.lupa.profile.data.entity.Login
import com.lupa.profile.data.remote.request.LoginRequest
import com.lupa.profile.data.remote.sources.ProfileDataSource
import okhttp3.internal.closeQuietly

class ProfileRepositoryImpl(private val dataSources: ProfileDataSource) : ProfileRepository {

    private val disposables = CompositeDisposable()

    //backing prperties
    private var _userStateEventManager: MutableStateEventManager<User> = MutableStateEventManager()
    override val userStateEventManager: StateEventManager<User>
        get() = _userStateEventManager

    private var _loginStateEventManager: MutableStateEventManager<Login> =
        MutableStateEventManager()
    override val loginStateEventManager: StateEventManager<Login>
        get() = _loginStateEventManager

    override fun getUser() {
        println("get user..........")
        val disposable = dataSources.getUser().fetchStateEventSubscriber { stateEvent ->
            _userStateEventManager.post(stateEvent)
        }
        disposables.add(disposable)
    }

    override fun login(request: LoginRequest) {
        val disposable = dataSources.postLogin(request)
            .fetchStateEventSubscriber { stateEvent ->
                _loginStateEventManager.post(stateEvent)
            }
        disposables.add(disposable)
    }

    override fun saveToken(token: String) {
        DataPreferences.get.token=token
    }

    override fun close() {
        _userStateEventManager.closeQuietly()
        _loginStateEventManager.closeQuietly()
        disposables.dispose()
    }
}