package Model;

/**
 * The Module class represents a course module that a student can take.
 */
public class Module {
    private String moduleCode;
    private String moduleName;
    private String semester;

    /**
     * Creates a new Module object with the specified module code, module name, and semester.
     * @param moduleCode the code that identifies the module
     * @param moduleName the name of the module
     * @param semester the semester in which the module is offered
     */
    public Module(String moduleCode, String moduleName, String semester) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.semester = semester;
    }

    /**
     * Returns the module code.
     * @return the code that identifies the module
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Sets the module code.
     * @param moduleCode the code that identifies the module
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Returns the module name.
     * @return the name of the module
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Sets the module name.
     * @param moduleName the name of the module
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Returns the semester in which the module is offered.
     * @return the semester in which the module is offered
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Sets the semester in which the module is offered.
     * @param semester the semester in which the module is offered
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }
}