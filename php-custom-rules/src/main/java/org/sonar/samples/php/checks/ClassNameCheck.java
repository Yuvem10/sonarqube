/*
 * SonarQube PHP Plugin
 * Copyright (C) 2016-2023 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.samples.php.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.declaration.ClassDeclarationTree;
import org.sonar.plugins.php.api.tree.expression.IdentifierTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;
import org.sonar.plugins.php.api.visitors.PhpFile;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Example of implementation of a check by extending {@link PHPVisitorCheck}.
 * PHPVisitorCheck provides methods to visit nodes of the Abstract Syntax Tree
 * that represents the source code.
 * <p>
 * Those methods can be overridden to process information
 * related to node and issue can be created via the context that can be
 * accessed through {@link PHPVisitorCheck#context()}.
 */
@Rule(
  key = ClassNameCheck.KEY,
  priority = Priority.MAJOR,
  name = "The class name must be started by X_",
  tags = {"convention"}
// Description can either be given in this annotation or through HTML name <ruleKey>.html located in package
// src/resources/org/sonar/l10n/php/rules/<repositoryKey>
// description = "<p>The class name must be started by X_</p>"
)
public class ClassNameCheck extends PHPVisitorCheck {

  public static final String KEY = "S1";

  @Override
  public void visitClassDeclaration(ClassDeclarationTree tree) {
    IdentifierTree callee = tree.name();
    String className = callee.text();
    PhpFile file = this.context().getPhpFile();
    final String filename = file.filename();
    URI URI = file.uri();
    String path = URI.getPath();

    final String pathRegex = "\\/([a-zA-Z0-9]+)\\/" + filename;
    Pattern pattern = Pattern.compile(pathRegex);
    Matcher matcher = pattern.matcher(path);

    if (matcher.find()) {
      String parentFolder = matcher.group(1);
      char firstLetterParent = parentFolder.charAt(0);
      String firstLetterParentStr = Character.toString(firstLetterParent);
      String upperLetter = firstLetterParentStr.toUpperCase();
      String firstLetterOfClass = Character.toString(className.charAt(0));
      String secondLetterOfClass = Character.toString(className.charAt(1));
      try {
        String thirdLetterOfClass = Character.toString(className.charAt(2));
        String startClassName2 = firstLetterOfClass + secondLetterOfClass;
        String startClassName3 = firstLetterOfClass + secondLetterOfClass + thirdLetterOfClass;

        List<String> letters = new ArrayList<String>();
        letters.add("C"); // Controllers
        letters.add("M"); // Models
        letters.add("E"); // Entities

        String regex = "[A-Z]_[A-Z]";
        boolean regexTest = startClassName3.matches(regex);

        if (letters.contains(upperLetter) && !startClassName2.equals(upperLetter + "_") || letters.contains(upperLetter) && !regexTest) {
          context().newIssue(this, callee, "The class name must be started by " + upperLetter + "_[MAJ]");
        }
      } catch (Exception e) {
        context().newIssue(this, callee, "The class name must be started by " + upperLetter + "_[MAJ]");
      }

    }
    super.visitClassDeclaration(tree);
  }

}
