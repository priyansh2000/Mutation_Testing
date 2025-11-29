#!/bin/bash

# Mutation Testing Project Runner
# This script runs both MTesting and ITesting modules with complete mutation testing

echo "=================================="
echo "🧬 MUTATION TESTING PROJECT RUNNER"
echo "=================================="
echo ""

# Function to print section headers
print_section() {
    echo ""
    echo "=== $1 ==="
    echo ""
}

# Function to check command success
check_status() {
    if [ $? -eq 0 ]; then
        echo "✅ $1 completed successfully"
    else
        echo "❌ $1 failed"
        exit 1
    fi
}

# Start timer
start_time=$(date +%s)

print_section "PROJECT INITIALIZATION"
echo "📂 Current directory: $(pwd)"
echo "📋 Checking project structure..."

# Check if both modules exist
if [ ! -d "MTesting" ]; then
    echo "❌ MTesting module not found!"
    exit 1
fi

if [ ! -d "ITesting" ]; then
    echo "❌ ITesting module not found!"
    exit 1
fi

echo "✅ Both MTesting and ITesting modules found"

print_section "STEP 1: RUNNING MTESTING MODULE (UNIT TESTING)"

echo "📁 Entering MTesting directory..."
cd MTesting

echo "🧹 Step 1.1: Cleaning and compiling MTesting module..."
mvn clean compile
check_status "MTesting compilation"

echo "🧪 Step 1.2: Running unit tests..."
mvn test
check_status "MTesting unit tests"

echo "🧬 Step 1.3: Executing PIT mutation testing..."
mvn org.pitest:pitest-maven:mutationCoverage
check_status "MTesting mutation testing"

echo "✅ MTesting module completed successfully"

print_section "STEP 2: RUNNING ITESTING MODULE (INTEGRATION TESTING)"

echo "📁 Entering ITesting directory..."
cd ../ITesting

echo "🧹 Step 2.1: Cleaning and compiling ITesting module..."
mvn clean compile
check_status "ITesting compilation"

echo "🧪 Step 2.2: Running integration tests..."
mvn test
check_status "ITesting integration tests"

echo "🧬 Step 2.3: Executing PIT mutation testing..."
mvn org.pitest:pitest-maven:mutationCoverage
check_status "ITesting mutation testing"

echo "✅ ITesting module completed successfully"

print_section "STEP 3: GENERATING RESULTS SUMMARY"

# Return to root directory
cd ..

# Calculate execution time
end_time=$(date +%s)
execution_time=$((end_time - start_time))

echo "📊 MUTATION TESTING EXECUTION SUMMARY:"
echo ""
echo "🔧 MTesting Module (Unit Testing):"
echo "   📄 Report: MTesting/target/pit-reports/index.html"
echo "   📄 Details: MTesting/target/pit-reports/org.example/Main.java.html"
echo ""
echo "🔗 ITesting Module (Integration Testing):"
echo "   📄 Report: ITesting/target/pit-reports/index.html"
echo "   📄 Details: ITesting/target/pit-reports/org.example.Integration/"
echo ""
echo "⏱️ Total Execution Time: ${execution_time} seconds"
echo ""

print_section "OPENING REPORTS"

echo "🌐 Available reports:"
echo "   • MTesting: file://$(pwd)/MTesting/target/pit-reports/index.html"
echo "   • ITesting: file://$(pwd)/ITesting/target/pit-reports/index.html"
echo ""

# Check if browser is available and ask user
read -p "🚀 Would you like to open the MTesting report in browser? (y/n): " -n 1 -r
echo ""
if [[ $REPLY =~ ^[Yy]$ ]]; then
    if command -v xdg-open > /dev/null; then
        xdg-open "MTesting/target/pit-reports/index.html"
        echo "🌐 MTesting report opened in browser"
    elif command -v firefox > /dev/null; then
        firefox "MTesting/target/pit-reports/index.html" &
        echo "🌐 MTesting report opened in Firefox"
    else
        echo "⚠️ No browser found. Please manually open: MTesting/target/pit-reports/index.html"
    fi
fi

echo ""
read -p "🚀 Would you like to open the ITesting report in browser? (y/n): " -n 1 -r
echo ""
if [[ $REPLY =~ ^[Yy]$ ]]; then
    if command -v xdg-open > /dev/null; then
        xdg-open "ITesting/target/pit-reports/index.html"
        echo "🌐 ITesting report opened in browser"
    elif command -v firefox > /dev/null; then
        firefox "ITesting/target/pit-reports/index.html" &
        echo "🌐 ITesting report opened in Firefox"
    else
        echo "⚠️ No browser found. Please manually open: ITesting/target/pit-reports/index.html"
    fi
fi

print_section "EXECUTION COMPLETED"

echo "🎉 MUTATION TESTING PROJECT EXECUTION COMPLETED SUCCESSFULLY!"
echo ""
echo "📋 Summary:"
echo "   ✅ Both modules executed without errors"
echo "   ✅ All tests passed"
echo "   ✅ Mutation testing reports generated"
echo "   ✅ HTML reports ready for analysis"
echo ""
echo "📚 Next steps:"
echo "   1. Review the HTML reports in your browser"
echo "   2. Analyze mutation coverage results" 
echo "   3. Identify areas for test improvement"
echo "   4. Check the detailed project report: Detailed_Project_Report.md"
echo ""
echo "🚀 Happy testing!"

exit 0