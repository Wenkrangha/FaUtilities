package com.wenkrang.faClip.Module.FaWindow.interpreter;

import com.wenkrang.faClip.Module.FaData.FaData;
import com.wenkrang.faClip.Module.FaWindow.FaInventory;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.FaInvHandler;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.basic.*;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.extra.InvBasicEventHandler;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.extra.InvLockHandler;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.extra.InvMoveableHandler;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.extra.InvNoteHandler;

import java.io.InputStream;
import java.util.ArrayList;

public class FaInvInterpreter {
    private final FaWindowInstance faWindowInstance;

    private final ArrayList<FaInvHandler> pipe = new ArrayList<>();

    public FaInvInterpreter(FaWindowInstance instance) {
        faWindowInstance = instance;

        pipe.add(new InvIdHandler());
        pipe.add(new InvSizeHandler());
        pipe.add(new InvDesignHandler());
        pipe.add(new InvDefineHandler());
        pipe.add(new InvNameHandler());
        pipe.add(new InvLockHandler());
        pipe.add(new InvNoteHandler());
        pipe.add(new InvBasicEventHandler());
        pipe.add(new InvMoveableHandler());
    }

    public FaWindowInstance getFaWindowInstance() {
        return faWindowInstance;
    }

    public FaInventory interpreter(String path) {
        InputStream resource = faWindowInstance.getPlugin().getResource(path);

        FaData data = new FaData(resource);

        FaInventory faInventory = new FaInventory(faWindowInstance);

        pipe.forEach(handler -> handler.handle(faInventory, data, faWindowInstance));

        return faInventory;
    }
}
