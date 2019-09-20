package com.karolapp.ideaappkt.presenter.interfaces

import android.app.Activity

public interface LoginPresenter {
    fun handleLogin(name: String, password: String, activity: Activity)
}