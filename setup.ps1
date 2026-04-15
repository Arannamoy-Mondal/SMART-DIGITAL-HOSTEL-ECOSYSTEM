# --- Smart Digital Hostel Ecosystem Windows Setup Script ---

Write-Host "===============================================" -ForegroundColor Cyan
Write-Host "   Smart Digital Hostel Ecosystem Setup Tool   " -ForegroundColor Green
Write-Host "===============================================" -ForegroundColor Cyan

# 1. Check for Ollama and Pull Models
Write-Host "`n[1/4] Setting up Ollama Models..." -ForegroundColor Yellow
$ollamaCheck = Get-Command ollama -ErrorAction SilentlyContinue
if ($ollamaCheck) {
    Write-Host "Ollama found. Pulling models..."
    ollama pull gpt-oss
    ollama pull nomic-embed-text
    Write-Host "Ollama models are ready." -ForegroundColor Green
} else {
    Write-Host "Warning: Ollama is not installed. Please install it from ollama.com to use AI features." -ForegroundColor DarkYellow
}

# 2. Setup Backend (Spring Boot)
Write-Host "`n[2/4] Installing Backend Dependencies (Maven)..." -ForegroundColor Yellow
if (Test-Path "mvnw.cmd") {
    .\mvnw.cmd install -DskipTests
    Write-Host "Backend dependencies installed successfully." -ForegroundColor Green
} else {
    Write-Host "Error: mvnw.cmd not found. Make sure you are in the project root." -ForegroundColor Red
}

# 3. Setup Frontend (Next.js)
Write-Host "`n[3/4] Installing Frontend Dependencies (npm)..." -ForegroundColor Yellow
if (Test-Path "frontend") {
    Set-Location frontend
    npm install
    Set-Location ..
    Write-Host "Frontend dependencies installed successfully." -ForegroundColor Green
} elseif (Test-Path "package.json") {
    npm install
    Write-Host "Frontend dependencies installed successfully." -ForegroundColor Green
} else {
    Write-Host "Warning: Frontend directory or package.json not found." -ForegroundColor DarkYellow
}

# 4. Final Verification
Write-Host "`n[4/4] Environment Check..." -ForegroundColor Yellow
java -version
node -v
npm -v

Write-Host "`n===============================================" -ForegroundColor Cyan
Write-Host "    Setup Complete! Project is ready to go.    " -ForegroundColor Green
Write-Host "===============================================" -ForegroundColor Cyan
Write-Host "To start the backend: .\mvnw.cmd spring-boot:run" -ForegroundColor Yellow
Write-Host "To start the frontend: npm run dev" -ForegroundColor Yellow