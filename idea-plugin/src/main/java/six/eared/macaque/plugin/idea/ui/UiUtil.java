package six.eared.macaque.plugin.idea.ui;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBRadioButton;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.function.Consumer;

public class UiUtil {

    /**
     * addGroup
     *
     * @param container
     * @param groupName
     * @param custom
     */
    public static void addGroup(JPanel container, String groupName, Consumer<JPanel> custom) {
        JPanel inner = new JPanel(createMigLayout(4));
        custom.accept(inner);

        addGroup(container, groupName, inner, false);
    }

    /**
     * addGroup
     *
     * @param container
     */
    public static void addGroup(JPanel container, String groupName, JPanel inner, boolean wrap) {
        JPanel group = new JPanel(createMigLayout());
        group.setBorder(IdeBorderFactory.createTitledBorder(groupName));
        group.add(inner, fillX());

        container.add(group, fillX());
    }

    /**
     * 增加输入框
     *
     * @param container
     */
    public static EditorTextField addInputBox(JPanel container, String labelName) {
        EditorTextField textField = new EditorTextField();
        container.add(new JLabel(labelName));
        container.add(textField, fillX());
        container.add(new JLabel(), new CC().wrap());

        return textField;
    }

    public static CC fillX() {
        return new CC().growX().pushX();
    }

    public static CC fillY() {
        return new CC().growY().pushY();
    }

    public static void fillY(JPanel container) {
        container.add(new JPanel(), fillY());
    }

    //单选k
    public static JBCheckBox addSelectBox(JPanel container, String selectName, Consumer<JBCheckBox> consumer) {
        JBCheckBox checkBox = new JBCheckBox(selectName);
        consumer.accept(checkBox);

        container.add(checkBox, new CC().wrap());
        return checkBox;
    }

    public static MigLayout createMigLayout() {
        return createMigLayout("0!", "0!", "0");
    }

    public static MigLayout createMigLayout(int gapx) {
        return createMigLayout(gapx + "px",
                "0!", "0");
    }

    public static MigLayout createMigLayout(String gapx, String gapy, String inset) {
        LC lc = new LC();
        lc.fill();
        lc.gridGap(gapx, gapy)
                .insets(inset);

        return new MigLayout(lc);
    }

    public static MigLayout createMigLayoutVertical() {
        LC lc = new LC();
        lc.flowY().fill().gridGap("0!", "0!")
                .insets("0");

        return new MigLayout(lc);
    }

    public static ComboBox<String> addDropdownSelectBox(JPanel container, String name, Consumer<ComboBox<String>> custom) {
        ComboBox<String> comboBox = new ComboBox<>();
        custom.accept(comboBox);

        container.add(new JLabel(name));
        container.add(comboBox, new CC().wrap());
        container.add(new JLabel(), new CC().wrap());
        return comboBox;
    }

    /**
     * 添加单选框-模式选择
     * @param container
     * @return
     */
    public static JBRadioButton[] addModeChangeRadio(JPanel container){
        ButtonGroup group = new ButtonGroup();

        JBRadioButton localMode = new JBRadioButton();
        localMode.setText("Local");

        JBRadioButton remoteMode = new JBRadioButton();
        remoteMode.setText("Remote");
        group.add(localMode);
        group.add(remoteMode);

        container.add(new JLabel("Plugin Mode"));
        container.add(localMode);
        container.add(remoteMode, fillX());
        container.add(new JLabel(), new CC().wrap());
        localMode.setSelected(true);

        return new JBRadioButton []{localMode,remoteMode};
    }
}
