"use client";

import React, { useState } from "react";

export default function CreateRolePage() {

  const [role, setRole] = useState("");

  const [msg, setMsg] = useState("");
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setMsg("");
    setSuccess(false);


    if (!role.trim()) {
      setMsg("Role name cannot be empty.");
      return;
    }

    setLoading(true);

    try {
  
      const response = await fetch("http://localhost:8001/role/create", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },

        body: JSON.stringify({ role: role }), 
      });

      const result = await response.text();

      if (response.ok) {
        setSuccess(true);
        setMsg(`Role '${role}' created successfully!`);
        setRole("");
      } else {
        setSuccess(false);
        setMsg(result || "Failed to create role. It might already exist.");
      }
    } catch (err) {
      setSuccess(false);
      setMsg("Connection Error! Is your backend running?");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-slate-50 flex items-center justify-center p-4 text-gray-800">
      <div className="card w-full max-w-md bg-white shadow-[0_10px_40px_rgba(8,_112,_184,_0.08)] border border-gray-100 overflow-hidden">
        
        {/* Header Section */}
        <div className="bg-primary p-6 text-white text-center">
          <div className="w-12 h-12 bg-white/20 rounded-xl flex items-center justify-center text-xl font-bold mx-auto mb-3">
            ⚙️
          </div>
          <h2 className="text-xl font-bold tracking-tight">Create System Role</h2>
          <p className="text-primary-content/80 text-xs mt-1">
            Define a new access role for the system.
          </p>
        </div>

        {/* Form Section */}
        <div className="p-6 md:p-8">
          <form onSubmit={handleSubmit} className="space-y-5">
            
            <div className="form-control">
              <label className="label-text font-semibold mb-2 text-gray-700">Role Name</label>
              <input 
                type="text" 
                placeholder="e.g., admin, manager, user" 
                className="input input-bordered w-full focus:input-primary bg-white text-gray-800 h-12 uppercase" 
                value={role} 
                onChange={(e) => setRole(e.target.value)} 
              />
              <span className="text-xs text-gray-500 mt-2">
                * Type the role name exactly as your system requires (usually lowercase or uppercase without spaces).
              </span>
            </div>

            {msg && (
              <div className={`alert ${success ? "alert-success" : "alert-error"} text-white transition-all py-2 text-sm`}>
                <span>{msg}</span>
              </div>
            )}

            <button 
              type="submit" 
              className={`btn btn-primary w-full text-white font-bold h-12 mt-2 ${loading ? "loading" : ""}`} 
              disabled={loading}
            >
              {loading ? "Creating Role..." : "Create Role"}
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}