/*
 * Copyright (C) 2017 Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.generic_recycler_adapter.holder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.generic_recycler_adapter.base.GenericRecyclerViewAdapter;
import com.generic_recycler_adapter.listener.BaseRecyclerListener;
import java.util.List;

/**
 * Base ViewHolder to be used with the generic adapter.
 * {@link GenericRecyclerViewAdapter}
 *
 * @param <T> type of objects, which will be used in the adapter's data set
 * @param <L> click listener {@link BaseRecyclerListener}
 */
public abstract class BaseViewHolder<T, L extends BaseRecyclerListener> extends RecyclerView.ViewHolder {

    private L listener;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(View itemView, L listener) {
        super(itemView);
        this.listener = listener;
    }

    /**
     * Bind data to the item.
     * Make sure not to perform any expensive operations here.
     *
     * @param item object, associated with the item.
     */
    public abstract void onBind(T item);

    /**
     * Bind data to the item.
     * Override this method for using the payloads in order to achieve the full power of DiffUtil*
     * @param item object, associated with the item.
     */
    public void onBind(T item, List<Object> payloads) {
        onBind(item);
    }

    protected L getListener() {
        return listener;
    }
}