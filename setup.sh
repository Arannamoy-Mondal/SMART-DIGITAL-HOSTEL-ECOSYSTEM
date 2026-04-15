#!/bin/bash

# --- Color Codes for Output ---
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}===============================================${NC}"
echo -e "${GREEN}   Smart Digital Hostel Ecosystem Setup Tool   ${NC}"
echo -e "${BLUE}===============================================${NC}"

# 1. Check for Ollama and Pull Models
echo -e "\n${YELLOW}[1/4] Setting up Ollama Models...${NC}"
if command -v ollama &> /dev/null
then
    echo "Ollama is installed. Pulling models..."
    ollama pull gpt-oss
    ollama pull nomic-embed-text
    echo -e "${GREEN}Ollama models are ready.${NC}"
else
    echo -e "${YELLOW}Warning: Ollama is not installed or not in PATH. Please install it to use AI features.${NC}"
fi

# 2. Setup Backend (Spring Boot)
echo -e "\n${YELLOW}[2/4] Installing Backend Dependencies (Maven)...${NC}"
if [ -f "mvnw" ]; then
    chmod +x mvnw
    ./mvnw install -DskipTests
    echo -e "${GREEN}Backend dependencies installed successfully.${NC}"
else
    echo -e "${YELLOW}Error: mvnw not found. Make sure you are in the project root.${NC}"
fi

# 3. Setup Frontend (Next.js)
echo -e "\n${YELLOW}[3/4] Installing Frontend Dependencies (npm)...${NC}"
if [ -d "frontend" ]; then
    cd frontend
    npm install
    cd ..
    echo -e "${GREEN}Frontend dependencies installed successfully.${NC}"
else
    # If Next.js is in the root directory
    if [ -f "package.json" ]; then
        npm install
        echo -e "${GREEN}Frontend dependencies installed successfully.${NC}"
    else
        echo -e "${YELLOW}Warning: Frontend directory or package.json not found.${NC}"
    fi
fi

# 4. Final Verification
echo -e "\n${YELLOW}[4/4] Environment Check...${NC}"
java -version
node -v
npm -v

echo -e "${BLUE}===============================================${NC}"
echo -e "${GREEN}    Setup Complete! Happy Coding!   ${NC}"
echo -e "${BLUE}===============================================${NC}"
echo -e "To start the backend: ${YELLOW}./mvnw spring-boot:run${NC}"
echo -e "To start the frontend: ${YELLOW}npm run dev${NC}"