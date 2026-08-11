package com.wenkrang.faClip.Module.FaWindow.interpreter.handler.extra;

import com.wenkrang.faClip.Module.FaData.FaData;
import com.wenkrang.faClip.Module.FaData.FaInventoryData;
import com.wenkrang.faClip.Module.FaInterface.FaInterfaceInstance;
import com.wenkrang.faClip.Module.FaInterface.FaIntf;
import com.wenkrang.faClip.Module.FaInterface.FaIntfContext;
import com.wenkrang.faClip.Module.FaWindow.FaInventory;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.Module.FaWindow.helper.WinDataGetter;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.FaInvHandler;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class InvBasicEventHandler implements FaInvHandler {
    public ArrayList<String> eventList = new ArrayList<>(List.of("init","close"));

    public static void invoke(Event event, Inventory inventory, String name, FaWindowInstance faWindowInstance) {
        // 检查是否为FaInventory
        if (WinDataGetter.isFaInventory(inventory)) {
            FaInventoryData faInventoryData = (FaInventoryData) inventory.getHolder();

            // 检查是否有指定的事件
            if (faInventoryData != null && faInventoryData.has(name)) {
                String node = faInventoryData.get(name);

                // 获取FaInterfaceInstance
                FaInterfaceInstance faInterfaceInstance = faWindowInstance.getFaInterfaceInstance();

                // 获取FaIntf列表
                List<FaIntf> intf = faInterfaceInstance.getIntf(node);

                // 检查FaIntf列表是否为空
                if (!intf.isEmpty()) {
                    FaIntf first = intf.getFirst();

                    // 创建FaIntfContext
                    FaIntfContext faIntfContext = new FaIntfContext();

                    // 设置事件
                    faIntfContext.set("event", event);

                    faIntfContext.set("win", WinDataGetter.getFaWin(inventory));

                    // 调用FaIntf
                    try {
                        first.invoke(null, faIntfContext, new String[0]);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        for (String event : eventList) {
            String node = faData.getString("event." + event);

            if (node != null) {
                faInventory.getEvents().put(event, node);
            }
        }
    }
}
