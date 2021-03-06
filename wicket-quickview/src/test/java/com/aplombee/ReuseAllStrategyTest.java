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
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  @author Vineet Semwal
 */
public class ReuseAllStrategyTest extends AbstractItemsNavigationStrategyTest{

    @Test(groups = {"wicketTests"} )
    public void addItems_1(){
        super.assertAddItems(new ReuseAllStrategy());
    }

    @Test(groups = {"wicketTests"})
    public void isPaging_1(){
        super.assertIsAddItemsSupported(new ReuseAllStrategy());
    }

    @Test(groups = {"wicketTests"})
    public void isZeroPageCreatedOnReRender_1(){
        Assert.assertFalse(new ReuseAllStrategy().isAlwaysZeroPageCreatedOnRender());
    }

    /**
     * existing items empty
     */
    @Test(groups = {"wicketTests"} )
    public void getItems_1(){
        IQuickReuseStrategy strategy=new ReuseAllStrategy();
        List<Integer> list=new ArrayList<Integer>();
        list.add(45);
        list.add(76);
        List<Item<Integer>>existingItems=new ArrayList<Item<Integer>>();
        IItemFactory factory= Mockito.mock(IItemFactory.class);
        final int index=0;
        final int index2=1;
        IModel<Integer> model1=new Model<Integer>(list.get(0));
        IModel<Integer>model2=new Model<Integer>(list.get(1));
        Item item1=new Item("0",0,model1) ;
        Mockito.when(factory.newItem(0,model1)).thenReturn(item1);
        Item item2=new Item("1",index2,model2);
        Mockito.when(factory.newItem(index2,model2)).thenReturn(item2);
        List<IModel<Integer>>newModels=new ArrayList<IModel<Integer>>();
        newModels.add(model1);
        newModels.add(model2);

        Iterator<Item<Integer>> actual=  strategy.getItems(factory,newModels.iterator(),existingItems.iterator());
        Assert.assertEquals(actual.next(), item1);
        Assert.assertEquals(actual.next(),item2);
        Mockito.verify(factory,Mockito.times(1)).newItem(index,model1);
        Mockito.verify(factory,Mockito.times(1)).newItem(index2,model2);


    }

    /**
     * existing items not empty
     */
    @Test(groups = {"wicketTests"} )
    public void getItems_2(){
        IQuickReuseStrategy strategy=new ReuseAllStrategy();
        List<Integer> list=new ArrayList<Integer>();
        list.add(45);
        list.add(76);
        IModel<Integer> model1=new Model<Integer>(list.get(0));
        IModel<Integer>model2=new Model<Integer>(list.get(1));
        final int index=70;
        final int index2=56;
        Item item1=new Item("70",index,model1) ;
        Item item2=new Item("56",index2,model2);
        List<Item<Integer>>existingItems=new ArrayList<Item<Integer>>();
        existingItems.add(item1);
        existingItems.add(item2);

        Iterator<Item<Integer>> actual=  strategy.getItems(null,null,existingItems.iterator());
        Assert.assertEquals(actual.next(), item1);
        Assert.assertEquals(actual.next(),item2);

    }


}
