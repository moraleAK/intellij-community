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
package com.siyeh.ig.threading;

import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiStatement;
import com.intellij.psi.PsiSynchronizedStatement;
import com.intellij.psi.util.PsiUtil;
import com.siyeh.InspectionGadgetsBundle;
import com.siyeh.ig.BaseInspection;
import com.siyeh.ig.BaseInspectionVisitor;
import org.jetbrains.annotations.NotNull;

public class EmptySynchronizedStatementInspection extends BaseInspection {

    @NotNull
    public String getDisplayName() {
        return InspectionGadgetsBundle.message(
                "empty.synchronized.statement.display.name");
    }

    @NotNull
    protected String buildErrorString(Object... infos) {
        return InspectionGadgetsBundle.message(
                "empty.synchronized.statement.problem.descriptor");
    }

    public BaseInspectionVisitor buildVisitor() {
        return new EmptySynchronizedStatementVisitor();
    }

    private static class EmptySynchronizedStatementVisitor
            extends BaseInspectionVisitor {

        @Override public void visitSynchronizedStatement(
                @NotNull PsiSynchronizedStatement statement) {
            super.visitSynchronizedStatement(statement);
            if (PsiUtil.isInJspFile(statement.getContainingFile())) {
                return;
            }
            final PsiCodeBlock body = statement.getBody();
            if (body == null) {
                return;
            }
            final PsiStatement[] statements = body.getStatements();
            if (statements.length > 0) {
                return;
            }
            registerStatementError(statement);
        }
    }
}