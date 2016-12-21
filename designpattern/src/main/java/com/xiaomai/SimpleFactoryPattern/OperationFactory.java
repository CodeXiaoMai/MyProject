
package com.xiaomai.SimpleFactoryPattern;

/**
 * Created by XiaoMai on 2016/12/21 15:52.
 */
public class OperationFactory {

    public static final String OPERATION_ADD = "OPERATION_ADD";

    public static final String OPERATION_SUB = "OPERATION_SUB";

    public static final String OPERATION_MUL = "OPERATION_MUL";

    public static final String OPERATION_DIV = "OPERATION_DIV";

    public static Operation createOperate(String operate) {
        Operation operation = null;
        switch (operate) {
            case OPERATION_ADD:
                operation = new OperationAdd();
                break;
            case OPERATION_SUB:
                operation = new OperationSub();
                break;
            case OPERATION_MUL:
                operation = new OperationMul();
                break;
            case OPERATION_DIV:
                operation = new OperationDiv();
                break;
        }
        return operation;
    }
}
