# clean_commit.ps1

# 1. Backup all .java files
Get-ChildItem -Recurse -Filter *.java | ForEach-Object {
    Copy-Item $_.FullName ($_.FullName + ".bak")
}

# 2. Remove all // and /* */ comments
Get-ChildItem -Recurse -Filter *.java | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file -Raw

    # Remove /* ... */ multi-line comments
    $content = [System.Text.RegularExpressions.Regex]::Replace($content, '/\*.*?\*/', '', 'Singleline')

    # Remove lines starting with //
    $lines = $content -split "`r?`n" | Where-Object { $_ -notmatch '^\s*//' }

    # Save cleaned content
    Set-Content $file $lines
}

# 3. Git add & commit
git add .
git commit -m "Commit without comments"

# 4. Restore original files from backup
Get-ChildItem -Recurse -Filter *.java.bak | ForEach-Object {
    $original = $_.FullName -replace '\.bak$',''
    Move-Item $_.FullName $original -Force
}
