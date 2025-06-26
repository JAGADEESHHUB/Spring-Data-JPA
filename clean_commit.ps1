# 1. Backup all .java files
Get-ChildItem -Recurse -Filter *.java | ForEach-Object {
    Copy-Item $_.FullName ($_.FullName + ".bak")
}

# 2. Remove all // and /* */ comments
Get-ChildItem -Recurse -Filter *.java | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file -Raw

    # Remove multi-line comments: /* ... */
    $content = [System.Text.RegularExpressions.Regex]::Replace($content, '/\*.*?\*/', '', 'Singleline')

    # Split into lines and remove lines starting with // or having inline //
    $lines = $content -split "`r?`n" | ForEach-Object {
        $_ -replace '//.*$', ''  # Remove everything after //
    }

    # Save cleaned content
    Set-Content $file $lines
}

# 3. Git add & commit (but .bak files will now be ignored)
git add .
git commit -m "Service and Repo"

# 4. Restore original .java files
Get-ChildItem -Recurse -Filter *.java.bak | ForEach-Object {
    $original = $_.FullName -replace '\.bak$',''
    Move-Item $_.FullName $original -Force
}
