package org.drulabs.myshelf.ui.common

import android.content.Context
import org.drulabs.myshelf.data.DataProviderImpl
import org.drulabs.myshelf.ui.home.HomeContract
import org.drulabs.myshelf.ui.home.HomePresenter

object PresenterCreator {

    fun createHomePresenter(view: HomeContract.View, context: Context): HomePresenter {
        val dataProvider = DataProviderImpl(context)
        return HomePresenter(view, dataProvider)
    }
}