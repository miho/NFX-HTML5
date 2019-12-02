/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.nfxhtml5.processrunner;

import eu.mihosoft.nfxhtml5.processrunner.util.ProcessRunnerImpl;
import java.io.File;
import java.io.PrintStream;

/**
 * Executes native process.
 * 
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public interface ProcessRunner {

    /**
     * Destroys the currently running tcc process.
     */
    void destroy();

    /**
     * Returns the process of the current tcc execution.
     * @return the process of the current tcc execution
     */
    Process getProcess();

    /**
     * Returns the working directory
     * @return the working directory
     */
    File getWorkingDirectory();

    /**
     * Prints the tcc output to the specified print streams.
     * @param out standard output stream
     * @param err error output stream
     * @return this interpreter
     */
    ProcessRunner print(PrintStream out, PrintStream err);

    /**
     * Prints the tcc output to the standard output.
     * @return this interpreter
     */
    ProcessRunner print();

    /* TODO 19.02.2018 add support for external headers and libraries
    CInterpreter addIncludeFolder(File folder);

    CInterpreter addLibraryFolder(File folder);

    CInterpreter addLibrary(String libName);
    */

    /**
     * Waits until the tcc process terminates.
     * @return this interpreter
     */
    ProcessRunner waitFor();


    /**
     * Enables debug output (for all subsequent interpreter calls).
     */
    static void debug() {
        ProcessRunnerImpl.setGlobalDebug(true);
    }

    /**
     * Enables debug output (for all subsequent interpreter calls).
     * @param b state to set
     */
    static void debug(boolean b) {
        ProcessRunnerImpl.setGlobalDebug(b);
    }
    

    


    /**
     * Executes process with the specified arguments.
     *
     * @param arguments arguments
     * @return process
     */
    static ProcessRunner execute(String... arguments) {
        return ProcessRunnerImpl.execute(null, arguments);
    }

    static ProcessRunner execute(String arguments) {
        return ProcessRunnerImpl.execute(null,arguments);
    }

    static ProcessRunner execute(File wd, String arguments) {
        return ProcessRunnerImpl.execute(wd,arguments);
    }


    /**
     * Returns the process installation folder.
     *
     * @return the process installation folder
     */
    static File getDistributionInstallationFolder() {
        return ProcessRunnerImpl.getDistRootPath();
    }
}
