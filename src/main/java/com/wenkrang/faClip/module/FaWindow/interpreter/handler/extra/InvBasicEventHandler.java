package com.wenkrang.faClip.module.FaWindow.interpreter.handler.extra;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaData.FaInventoryData;
import com.wenkrang.faClip.module.FaInterface.FaInterfaceInstance;
import com.wenkrang.faClip.module.FaInterface.FaIntf;
import com.wenkrang.faClip.module.FaInterface.FaIntfContext;
import com.wenkrang.faClip.module.FaMessage.Fm;
import com.wenkrang.faClip.module.FaMessage.exception.FaException;
import com.wenkrang.faClip.module.FaMessage.exception.FaWindowException;
import com.wenkrang.faClip.module.FaMessage.helper.I18nHelper;
import com.wenkrang.faClip.module.FaWindow.FaInventory;
import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.module.FaWindow.helper.WinDataHelper;
import com.wenkrang.faClip.module.FaWindow.interpreter.handler.FaInvHandler;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class InvBasicEventHandler implements FaInvHandler {
    public ArrayList<String> eventList = new ArrayList<>(List.of("init","close"));

    public static void invoke(Event event, Inventory inventory, String name, FaWindowInstance faWindowInstance) {
        // 检查是否为FaInventory
        if (WinDataHelper.isFaInventory(inventory)) {
            FaInventoryData faInventoryData = (FaInventoryData) inventory.getHolder();

            // 检查是否有指定的事件
            if (faInventoryData != null && faInventoryData.has("event." + name)) {
                String node = faInventoryData.get("event." + name);

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

                    faIntfContext.set("win", WinDataHelper.getFaWin(inventory));

                    // 调用FaIntf
                    try {
                        first.invoke(null, faIntfContext, new String[0]);
                    } catch (InvocationTargetException e) {
                        // 解包业务方法抛出的异常：FaException 原样上抛，其余包装为 FaWindowException
                        Throwable cause = e.getCause() != null ? e.getCause() : e;
                        if (cause instanceof FaException faException) throw faException;
                        throw new FaWindowException("FaWindow.Error.Event.InvokeFailed", cause,
                                name, cause.getMessage());
                    } catch (IllegalAccessException e) {
                        throw new FaWindowException("FaWindow.Error.Event.InvokeFailed", e,
                                name, e.getMessage());
                    }
                }else {
                    Fm.warning(I18nHelper.ft("FaInterface.Exception.Instance.IntfNotFound", node));
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
