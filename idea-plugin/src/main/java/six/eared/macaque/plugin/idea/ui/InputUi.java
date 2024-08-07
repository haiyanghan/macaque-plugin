package six.eared.macaque.plugin.idea.ui;


import com.intellij.ui.EditorTextField;
import com.intellij.ui.JBColor;
import six.eared.macaque.common.util.StringUtil;

import javax.swing.*;

import java.awt.*;

import static six.eared.macaque.plugin.idea.ui.UiUtil.*;

public class InputUi extends JPanel {

    private String name;

    private JLabel label;

    private EditorTextField  textField;

    private boolean required = false;

    private JLabel errMsgLabel = null;

    public InputUi(String name) {
        this(name, "");
    }

    public InputUi(String name, String value) {
        super(createMigLayout());
        this.name = name;
        this.textField = new EditorTextField(value);
        this.label = createEqualWidthLabel(name);
        this.add(this.label);
        this.add(this.textField, fillX());

        this.errMsgLabel = new JLabel("");
        this.errMsgLabel.setPreferredSize(new Dimension(100, 30));
        this.errMsgLabel.setForeground(Color.red);
        this.add(this.errMsgLabel);
    }

    public String getName() {
        return this.name;
    }

    public void setValue(String value) {
        this.textField.setText(value);
    }

    public String getValue() {
        return this.textField.getText();
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean checkRequired() {
        if (this.required) {
            if (StringUtil.isEmpty(getValue())) {
                errMsgLabel.setText("* required");
                return false;
            }else{
                errMsgLabel.setText("");
            }
        }
        return true;
    }

    public void reset() {
        this.textField.setBorder(null);
    }
}
