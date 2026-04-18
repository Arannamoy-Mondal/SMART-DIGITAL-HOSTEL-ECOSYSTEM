"use client";

import React, { useState } from "react";
import Link from "next/link";

export default function LoginPage() {
  const [form, setForm] = useState({
    userName: "",
    password: "",
  });

  const [msg, setMsg] = useState("");
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setMsg("");
    setSuccess(false);

    if (!form.userName || !form.password) {
      setMsg("Please enter both username and password.");
      return;
    }

    setLoading(true);

    try {
      const response = await fetch("http://localhost:8001/user/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(form),
      });
      const result = await response.text(); 

      if (response.ok) {
        setSuccess(true);
        setMsg("Login successful! Redirecting...");
        console.log(result);
        localStorage.setItem("token", result);
      } else {
        setSuccess(false);
        setMsg(result || "Invalid username or password.");
      }
    } catch (err) {
      setSuccess(false);
      setMsg("Connection Error! Please make sure your Spring Boot backend is running.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-slate-50 flex items-center justify-center p-4 md:p-6 text-gray-800">
      <div className="card w-full max-w-4xl bg-white shadow-[0_20px_50px_rgba(8,_112,_184,_0.1)] border border-gray-100 overflow-hidden">
        <div className="flex flex-col md:flex-row">
          
          {/* Left Side: Branding */}
          <div className="md:w-1/2 bg-primary p-10 text-white flex flex-col justify-center items-center text-center space-y-6">
            <div className="w-20 h-20 bg-white/20 rounded-3xl flex items-center justify-center text-4xl font-extrabold shadow-lg">
              SH
            </div>
            <div>
              <h2 className="text-3xl font-bold tracking-tight mb-2">Welcome Back!</h2>
              <p className="text-primary-content/80 text-sm md:text-base px-4">
                Login to your Smart Hostel dashboard to access your profile and manage your stay.
              </p>
            </div>
          </div>

          {/* Right Side: Login Form */}
          <div className="md:w-1/2 p-8 md:p-12 flex flex-col justify-center">
            <h3 className="text-2xl font-bold text-gray-800 mb-6 text-center md:text-left">
              Account Login
            </h3>

            <form onSubmit={handleSubmit} className="space-y-5">
              
              <div className="form-control">
                <label className="label-text font-semibold mb-1 text-gray-700">Username</label>
                <input 
                  type="text" 
                  placeholder="Enter your username" 
                  className="input input-bordered w-full focus:input-primary bg-white text-gray-800 h-12" 
                  value={form.userName} 
                  onChange={(e) => setForm({ ...form, userName: e.target.value })} 
                />
              </div>

              <div className="form-control">
                <label className="label-text font-semibold mb-1 text-gray-700">Password</label>
                <input 
                  type="password" 
                  placeholder="••••••••" 
                  className="input input-bordered w-full focus:input-primary bg-white text-gray-800 h-12" 
                  value={form.password} 
                  onChange={(e) => setForm({ ...form, password: e.target.value })} 
                />
                <div className="flex justify-end mt-2">
                  <a href="#" className="text-sm text-primary hover:underline font-medium">Forgot Password?</a>
                </div>
              </div>

              {msg && (
                <div className={`alert ${success ? "alert-success" : "alert-error"} text-white transition-all py-2 text-sm`}>
                  <span>{msg}</span>
                </div>
              )}

              <button 
                type="submit" 
                className={`btn btn-primary w-full text-white font-bold h-12 mt-4 ${loading ? "loading" : ""}`} 
                disabled={loading}
              >
                {loading ? "Verifying..." : "Sign In"}
              </button>

              <div className="text-center mt-6 text-sm text-gray-600">
                Don't have an account?{" "}
                <Link href="/customer/signup" className="text-primary font-bold hover:underline">
                  Create one now
                </Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}