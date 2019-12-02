package eu.mihosoft.nativefx.html5;

import eu.mihosoft.nativefx.NativeNode;
import eu.mihosoft.nfxhtml5.processrunner.ProcessRunner;

import java.io.File;
import java.util.UUID;

/**
 * HTML5 render engine based on chromium/qt-webengine. In contrast to the WebView implementation that comes with
 * JavaFX this implementation uses multiple processes and shared memory to render full HTM5 content, including WebGL.
 */
public final class HTML5Engine {

    private final NativeNode view;
    private final ProcessRunner runner;
    private final String memName;

    /**
     * Opens the specified url.
     * @param url url to open
     * @return html5 engine that renders the specified url
     */
    public static HTML5Engine open(String url) {
        UUID id = UUID.randomUUID();
        String memName = "_nfx-html5_mem_"+id.toString();
        ProcessRunner runner = ProcessRunner.execute("-n",memName,"--url", url);
        //runner.print();
        NativeNode view = null;
        int counter = 0;
        int retries = 100;
        boolean notConnected = true;
        while(notConnected && counter < retries) {
            try {
                Thread.sleep(10);
                view = new NativeNode(true, false);
                view.connect(memName);
                notConnected = false;
            } catch (Exception e) {

            }
            counter++;
        }
        return new HTML5Engine(view, runner, memName);
    }

    private HTML5Engine(NativeNode view, ProcessRunner runner, String memName) {
        this.view = view;
        this.runner = runner;
        this.memName = memName;
    }


    /**
     * Returns the JavaFX view associated with this engine.
     * @return the JavaFX view associated with this engine
     */
    public NativeNode getView() {
        return this.view;
    }

    /**
     * Returns the process that renders the specified web content.
     * @return the process that renders the specified web content
     */
    public ProcessRunner getProcess() {
        return runner;
    }

    /**
     * Terminates the rendering process.
     */
    public void terminate() {
        this.view.terminate();
    }
}
