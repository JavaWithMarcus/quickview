/**
 *
 Copyright 2012 Vineet Semwal

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.aplombee;

import org.apache.wicket.markup.repeater.IItemFactory;
import org.apache.wicket.markup.repeater.IItemReuseStrategy;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import java.io.Serializable;
import java.util.Iterator;

/**
 *  Interface for item reuse strategies.
 * <p>
 * <u>Notice:</u> Child items will be rendered in the order they are provided by the returned
 * iterator, so it is important that the strategy preserve this order
 * </p>
 *
 * depending on use any new reuse strategy can be created ,few ready-made strategies for QuickView are <br/>
 *
 * 1) {@link DefaultQuickReuseStrategy}
 *
 *  <br/>
 * 2) {@link QuickReuseIfModelsEqualStrategy}
 *  <br/>
 *
 * 3) {@link ItemsNavigationStrategy}
 *   <br/>
 *
 * 4) {@link ReuseAllStrategy}
 *
 *
 * @author Vineet Semwal
 */
public interface IQuickReuseStrategy extends IItemReuseStrategy {

    /**
     * Returns an iterator over items that will be added to the view without re-rendering the QuickView
     *
     * @param <T>
     *            type of Item
     * @param startIndex index from where new items will be added
     *
     * @param factory
     *            implementation of IItemFactory
     * @param newModels
     *            iterator over models for items

     * @return iterator over items that will be added
     */
    <T> Iterator<Item<T>> addItems(int startIndex,IItemFactory<T> factory, Iterator<IModel<T>> newModels);



    /**
     *  tells whether reuse strategy support addition of items to view without re-rendering QuickView
     *
     * @return   boolean
     */
    boolean isAddItemsSupported();

    /**
     * if the strategy creates items for zero page every time view is rendered then returns true else false
     *
     * @return  true if zero page has to be created everytime view is rendered else false
     */
    boolean isAlwaysZeroPageCreatedOnRender();

}
