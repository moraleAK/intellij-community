/*
 * Copyright 2003-2007 Dave Griffith, Bas Leijdekkers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.siyeh.ig.initialization;

import com.intellij.psi.*;
import com.siyeh.InspectionGadgetsBundle;
import com.siyeh.ig.BaseInspection;
import com.siyeh.ig.BaseInspectionVisitor;
import com.siyeh.ig.InspectionGadgetsFix;
import com.siyeh.ig.fixes.MakeInitializerExplicitFix;
import com.siyeh.ig.psiutils.ClassUtils;
import com.siyeh.ig.psiutils.InitializationUtils;
import com.siyeh.ig.ui.SingleCheckboxOptionsPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class StaticVariableInitializationInspection extends BaseInspection {

    /** @noinspection PublicField*/
    public boolean m_ignorePrimitives = false;

    @NotNull
    public String getID(){
        return "StaticVariableMayNotBeInitialized";
    }

    @NotNull
    public String getDisplayName() {
        return InspectionGadgetsBundle.message(
                "static.variable.may.not.be.initialized.display.name");
    }

    @NotNull
    public String buildErrorString(Object... infos) {
        return InspectionGadgetsBundle.message(
                "static.variable.may.not.be.initialized.problem.descriptor");
    }

    public JComponent createOptionsPanel() {
        return new SingleCheckboxOptionsPanel(InspectionGadgetsBundle.message(
                "primitive.fields.ignore.option"),
                this, "m_ignorePrimitives");
    }

    public InspectionGadgetsFix buildFix(PsiElement location){
        return new MakeInitializerExplicitFix();
    }

    public BaseInspectionVisitor buildVisitor() {
        return new StaticVariableInitializationVisitor();
    }

    private class StaticVariableInitializationVisitor
            extends BaseInspectionVisitor {

        @Override public void visitField(@NotNull PsiField field) {
            if (!field.hasModifierProperty(PsiModifier.STATIC)) {
                return;
            }
            if (field.getInitializer() != null) {
                return;
            }
            final PsiClass containingClass = field.getContainingClass();
            if (containingClass == null) {
                return;
            }
            if (containingClass.isEnum()) {
                return;
            }
            if (m_ignorePrimitives) {
                final PsiType type = field.getType();
                if (ClassUtils.isPrimitive(type)) {
                    return;
                }
            }
            final PsiClassInitializer[] initializers =
                    containingClass.getInitializers();
            for(final PsiClassInitializer initializer : initializers){
                if(initializer.hasModifierProperty(PsiModifier.STATIC)){
                    final PsiCodeBlock body = initializer.getBody();
                    if(InitializationUtils.blockAssignsVariableOrFails(body,
                            field)) {
                        return;
                    }
                }
            }
            registerFieldError(field);
        }
    }
}