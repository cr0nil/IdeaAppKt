package com.karolapp.ideaappkt.ui.presenter.handlers

import com.google.common.truth.Truth.assertThat
import com.karolapp.ideaappkt.ui.contract.AuthContract
import com.karolapp.ideaappkt.ui.view.RegisterFragment
import com.karolapp.ideaappkt.ui.view.presenter.RegisterPreseterImpl
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock

class RegisterPreseterImplTest {
    @Mock
    lateinit var authView : AuthContract.AuthView

    @Test
    fun validEmail() {
        val email: String = "AS"
        val loginViewMock: AuthContract.AuthView = mock(AuthContract.AuthView::class.java)
        assertThat(RegisterPreseterImpl(loginViewMock).validEmail(email)).isTrue()

    }


}